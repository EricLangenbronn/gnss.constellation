package fr.gnss.constellation.ouranos.sp3.service.parallel;


import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


@RequiredArgsConstructor
@Slf4j
public class Sp3ServiceParallel implements ISp3Service {

  private static final int NB_THREADS = 10;
  private static final int NB_MILLIS_TIMEOUT = 30000; // 30secondes

  // -------------------- Services --------------------

  private final ISp3FileDao sp3FileRepository;
  private final AuthorizedNewSp3Download authorizedNewSp3Download;
  private final Sp3DownloadAndStoreFactory sp3DownloadAndStoreFactory;
  private final ExecutorService executorService = Executors.newFixedThreadPool(NB_THREADS);

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

  @Override
  public boolean isSp3FileExisting(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName) != null;
  }

  @Override
  public Sp3File getSp3File(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName);
  }
}
