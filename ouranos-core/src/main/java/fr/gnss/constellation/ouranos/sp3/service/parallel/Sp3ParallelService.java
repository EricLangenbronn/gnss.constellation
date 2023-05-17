package fr.gnss.constellation.ouranos.sp3.service.parallel;


import fr.gnss.constellation.ouranos.common.service.parallel.ParallelRunnableService;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.AbstractSp3Service;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;


@Slf4j
public class Sp3ParallelService extends AbstractSp3Service {

  // -------------------- Services --------------------

  private final AuthorizedNewSp3Download authorizedNewSp3Download;
  private final Sp3DownloadAndStoreFactory sp3DownloadAndStoreFactory;
  private final ParallelRunnableService parallelRunnableService = new ParallelRunnableService();


  // ------------------------ Constructeur(s) ------------------------

  public Sp3ParallelService(ISp3FileDao sp3FileRepository, AuthorizedNewSp3Download authorizedNewSp3Download
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
    Collection<Runnable> tasksNotExecuted = parallelRunnableService.executeTasks(
        Optional.ofNullable(sp3FileNames).stream().flatMap(Collection::stream)
            .map(sp3DownloadAndStoreFactory::getSp3DownloadAndStoreTask)
            .collect(Collectors.toList())
    );

    if (!CollectionUtils.isEmpty(tasksNotExecuted)) {
      Optional.of(tasksNotExecuted).stream().flatMap(Collection::stream)
          .forEach(taskNotExecuted -> {
            Sp3DownloadAndStoreTask sp3DownloadAndStoreTask = (Sp3DownloadAndStoreTask) taskNotExecuted;
            log.error(String.format("Ce téléchargement n'a pas pu se faire [sp3FileName=%s]"
                , sp3DownloadAndStoreTask.getSp3FileName().getFileName(true)));
          });
    }
  }
}
