package fr.gnss.constellation.ouranos.sp3.service.parallel;


import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.AbstractSp3Service;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


@Slf4j
public class Sp3ServiceParallel extends AbstractSp3Service {

  // -------------------- Attributs --------------------

  private static final int NB_THREADS = 10;
  private static final int NB_MILLIS_TIMEOUT = 30000; // 30secondes

  // -------------------- Services --------------------

  private final AuthorizedNewSp3Download authorizedNewSp3Download;
  private final Sp3DownloadAndStoreFactory sp3DownloadAndStoreFactory;
  private final ExecutorService executorService = Executors.newFixedThreadPool(NB_THREADS);

  // ------------------------ Constructeur(s) ------------------------

  public Sp3ServiceParallel(ISp3FileDao sp3FileRepository, AuthorizedNewSp3Download authorizedNewSp3Download
      , Sp3DownloadAndStoreFactory sp3DownloadAndStoreFactory) {
    super(sp3FileRepository);
    this.authorizedNewSp3Download = authorizedNewSp3Download;
    this.sp3DownloadAndStoreFactory = sp3DownloadAndStoreFactory;

  }

  // -------------------- Methodes de l'interface --------------------

  @Override
  public void downloadsAndStoresIfNotExist(List<Sp3FileName> sp3FileNames) {

    if (!authorizedNewSp3Download.isAuthorized()) {
      log.warn("Download new SP3 files not authorized");
      return;
    }

    log.info("Parallel download Sp3 files");
    Optional.ofNullable(sp3FileNames).stream().flatMap(Collection::stream)
        .forEach(sp3FileName -> executorService.execute(sp3DownloadAndStoreFactory.getSp3DownloadAndStoreTask(sp3FileName)));
    executorService.shutdown();

    List<Runnable> tasksNotExecuted = new ArrayList<>();
    try {
      if (!executorService.awaitTermination(NB_MILLIS_TIMEOUT, TimeUnit.MILLISECONDS)) {
        tasksNotExecuted = executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      tasksNotExecuted = executorService.shutdownNow();
    }

    if (!CollectionUtils.isEmpty(tasksNotExecuted)) {
      Optional.ofNullable(tasksNotExecuted).stream().flatMap(Collection::stream)
          .forEach(taskNotExecuted -> {
            Sp3DownloadAndStoreTask sp3DownloadAndStoreTask = (Sp3DownloadAndStoreTask) taskNotExecuted;
            log.error(String.format("Ce téléchargement n'a pas pu se faire [sp3FileName=%s]"
                , sp3DownloadAndStoreTask.getSp3FileName().getFileName(true)));
          });
    }
  }
}
