package fr.gnss.constellation.ouranos.sp3.service.parallel;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.ISp3InputStreamDao;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import org.jboss.logging.Logger;

public class Sp3DownloadAndStoreTask implements Runnable {

  private static final Logger log = Logger.getLogger(Sp3DownloadAndStoreTask.class);

  private final ISp3InputStreamDao sp3InputStreamRepository;
  private final ISp3FileDao sp3FileRepository;
  private final Sp3FileName sp3FileName;

  public Sp3DownloadAndStoreTask(ISp3InputStreamDao sp3InputStreamRepository, ISp3FileDao sp3FileRepository, Sp3FileName sp3FileName) {
    this.sp3InputStreamRepository = sp3InputStreamRepository;
    this.sp3FileRepository = sp3FileRepository;
    this.sp3FileName = sp3FileName;
  }

  @Override
  public void run() {
    try {
      if (sp3FileRepository.getFile(sp3FileName) == null) {
        Sp3File newSp3File = sp3FileRepository.saveSp3File(sp3FileName, sp3InputStreamRepository.downloadSp3File(sp3FileName, true));
        log.info(String.format("Téléchargement reussi [newSp3File=%s]", newSp3File.getFile().getAbsoluteFile()));
      }
    } catch (Exception e) {
      log.error(String.format("Impossible de télécharger ou de stocker le fichier SP3 [sp3FileName=%s]", sp3FileName), e);
    }
  }

  public Sp3FileName getSp3FileName() {
    return sp3FileName;
  }
}
