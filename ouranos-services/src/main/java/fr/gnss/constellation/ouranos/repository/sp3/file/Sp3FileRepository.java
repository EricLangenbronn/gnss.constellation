package fr.gnss.constellation.ouranos.repository.sp3.file;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class Sp3FileRepository implements ISp3FileRepository {

    // -------------------- Attributs --------------------

    @Qualifier("sp3Directory")
    private final File sp3Directory;

    // -------------------- Initialisation --------------------

    @PostConstruct
    public void init() {
        checkAccessSp3Directory();
        log.info("Repertoire SP3 : " + sp3Directory);
    }

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<Sp3File> getListSp3File() {
        checkAccessSp3Directory();

        List<Sp3File> sp3FileNames = null;
        boolean hasSp3File = Optional.ofNullable(sp3Directory.listFiles(filter))
                .map(sp3Files -> sp3Files.length > 0)
                .orElse(false);
        if (hasSp3File) {
            sp3FileNames = new ArrayList<>();
            for (File sp3File : sp3Directory.listFiles(filter)) {
                log.debug(MessageFormat.format("Chargement fichier : {0}", sp3File.getName()));
                sp3FileNames.add(new Sp3File(sp3File));
            }
        }

        return sp3FileNames;
    }

    @Override
    public Sp3File getFile(Sp3FileName sp3FileName) {
        Path pathFile = Path.of(sp3Directory.getAbsolutePath(), sp3FileName.getFileName(false));

        Sp3File sp3File = null;
        if (checkAccessSp3File(pathFile.toFile())) {
            sp3File = new Sp3File(pathFile.toFile());
        }

        return sp3File;
    }

    @Override
    public Sp3File saveSp3File(Sp3FileName sp3FileName, InputStream inputStream) {
        checkAccessSp3Directory();

        if (!isSp3DirectoryWritable()) {
            throw new RuntimeException(MessageFormat.format("Impossible d'accéder en écriture au répertoire contenant les fichiers Sp3. [pathSp3Dir= {0}]", sp3Directory.getAbsolutePath()));
        }

        Path sp3File = Path.of(sp3Directory.getAbsolutePath(), sp3FileName.getFileName(false));
        try {
            Files.createFile(sp3File);
            FileUtils.copyInputStreamToFile(inputStream, sp3File.toFile());
        } catch (FileAlreadyExistsException e) {
            log.warn(MessageFormat.format("Le fichier sp3 existe déjà, on ne veut pas l'écraser. [sp3File={}]", sp3File.getFileName()), e);
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format("Erreur lors de la sauvegarde du fichier sp3. [sp3File={}]", sp3File.getFileName()), e);
        }

        return new Sp3File(sp3File.toFile());
    }

    @Override
    public boolean isSp3DirectoryWritable() {
        return sp3Directory.exists() && sp3Directory.isDirectory() && sp3Directory.canWrite();
    }

    // -------------------- Methodes internes --------------------

    private static final FilenameFilter filter = (directory, fileName) -> fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");

    private void checkAccessSp3Directory() {
        if (!sp3Directory.exists() || !sp3Directory.isDirectory() || !sp3Directory.canRead()) {
            throw new RuntimeException(MessageFormat.format("Impossible d'accéder au répertoire contenant les fichiers Sp3. [pathSp3Dir= {0}]", sp3Directory.getAbsolutePath()));
        }
    }

    private boolean checkAccessSp3File(File file) {
        return file.exists() && file.isFile() && file.canRead();
    }
}
