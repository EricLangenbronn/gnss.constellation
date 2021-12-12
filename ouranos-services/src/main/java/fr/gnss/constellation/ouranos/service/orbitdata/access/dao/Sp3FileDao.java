package fr.gnss.constellation.ouranos.service.orbitdata.access.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.logmessage.ILogMessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class Sp3FileDao implements ISp3FileDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sp3FileDao.class);

    // -------------------- Services --------------------

    private final ILogMessageService configurationLogMessageService;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<String> getListSp3FileName(String pathSp3Dir) {
        File fileSp3Dir = new File(pathSp3Dir);
        // TODO tester l'existance du fichier fileSp3Dir
        List<String> sp3FileNames = Arrays.asList(fileSp3Dir.list(filter));

        return sp3FileNames;
    }

    @Override
    public List<File> getListSp3File(String pathSp3Dir) {

        File fileSp3Dir = new File(pathSp3Dir);
        if (fileSp3Dir.exists()) {
            List<File> sp3FileNames = new ArrayList<>();

            // TODO tester l'existance du fichier fileSp3Dir
            if (fileSp3Dir.listFiles(filter) == null || fileSp3Dir.listFiles(filter).length == 0) {
                // on fait rien
            } else {
                for (File sp3File : fileSp3Dir.listFiles(filter)) {
                    LOGGER.info(configurationLogMessageService.getDefautErrorMessage("SFD.GLSF.LSF"), sp3File.getName());
                    sp3FileNames.add(sp3File);
                }
            }

            return sp3FileNames;
        } else {
            throw new RuntimeException(MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("SFD.GLSF.TE.DNE"), pathSp3Dir));
        }

    }

    @Override
    public File getFile(String repertoireSp3, Sp3FileName sp3FileName) {
        Path pathFile = Path.of(repertoireSp3, sp3FileName.getFileName(false));
        File sp3File = pathFile.toFile();

        if (sp3File.exists()) {
            // c'est ok
        } else {
            sp3File = null;
        }

        return sp3File;
    }

    // -------------------- Methodes internes --------------------

    private static FilenameFilter filter = new FilenameFilter() {
        public boolean accept(File directory, String fileName) {
            return fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");
        }
    };
}
