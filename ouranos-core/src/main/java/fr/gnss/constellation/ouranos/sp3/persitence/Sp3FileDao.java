package fr.gnss.constellation.ouranos.sp3.persitence;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class Sp3FileDao implements ISp3FileDao {

  // -------------------- Attributs --------------------

  private static final FilenameFilter filter = (directory, fileName) -> fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");

  // -------------------- Propriétés de la classe --------------------

  private final Sp3StorageDirectory defaultDownloadSp3Directory;

  // -------------------- Methodes de l'interface --------------------

  @PostConstruct
  public void init() {
    checkAccessSp3Directory();
    log.info("Repertoire SP3 : " + defaultDownloadSp3Directory.getAbsolutePath());
  }

  @Override
  public List<Sp3File> getListSp3File() {
    checkAccessSp3Directory();

    List<Sp3File> sp3FileNames = null;
    boolean hasSp3File = Optional.ofNullable(defaultDownloadSp3Directory.listFiles(filter))
        .map(sp3Files -> sp3Files.length > 0)
        .orElse(false);
    if (hasSp3File) {
      sp3FileNames = new ArrayList<>();
      for (File sp3File : defaultDownloadSp3Directory.listFiles(filter)) {
        log.debug(String.format("Chargement fichier : %s", sp3File.getName()));
        sp3FileNames.add(new Sp3File(sp3File));
      }
    }

    return sp3FileNames;
  }

  @Override
  public Sp3File getFile(Sp3FileName sp3FileName) {
    Path pathFile = Path.of(defaultDownloadSp3Directory.getAbsolutePath(), sp3FileName.getFileName(false));

    Sp3File sp3File = null;
    if (checkAccessSp3File(pathFile.toFile())) {
      sp3File = new Sp3File(pathFile.toFile());
    }

    return sp3File;
  }

  // -------------------- Methodes internes --------------------

  @Override
  public Sp3File saveSp3File(Sp3FileName sp3FileName, InputStream inputStream) {
    checkAccessSp3Directory();

    if (!defaultDownloadSp3Directory.isWritable()) {
      throw new RuntimeException(
          String.format("Impossible d'accéder en écriture au répertoire contenant les fichiers Sp3. [pathSp3Dir= %s]"
              , defaultDownloadSp3Directory.getAbsolutePath())
      );
    }

    Path sp3File = Path.of(defaultDownloadSp3Directory.getAbsolutePath(), sp3FileName.getFileName(false));
    try {
      Files.createFile(sp3File);
      FileUtils.copyInputStreamToFile(inputStream, sp3File.toFile());
    } catch (FileAlreadyExistsException e) {
      log.warn(String.format("Le fichier sp3 existe déjà, on ne veut pas l'écraser. [sp3File=%s]", sp3File.getFileName()), e);
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("Erreur lors de la sauvegarde du fichier sp3. [sp3File=%s]", sp3File.getFileName())
          , e
      );
    }

    return new Sp3File(sp3File.toFile());
  }

  private void checkAccessSp3Directory() {
    if (!defaultDownloadSp3Directory.isAccessible()) {
      throw new RuntimeException(
          String.format("Impossible d'accéder au répertoire contenant les fichiers Sp3. [pathSp3Dir= %s]"
              , defaultDownloadSp3Directory.getAbsolutePath())
      );
    }
  }

  private boolean checkAccessSp3File(File file) {
    return file.exists() && file.isFile() && file.canRead();
  }
}
