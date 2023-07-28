package fr.gnss.constellation.ouranos.sp3.service;

import fr.gnss.constellation.ouranos.sp3.infrastructure.sp3.ISp3InputStreamDao;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.parallel.Sp3DownloadAndStoreFactory;
import fr.gnss.constellation.ouranos.sp3.service.parallel.Sp3ParallelService;
import fr.gnss.constellation.ouranos.sp3.service.sequential.Sp3SequentialService;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class Sp3Configuration {

  @Produces
  public AuthorizedNewSp3Download getAuthorizedNewSp3Download(Sp3Properties sp3Properties) {
    return new AuthorizedNewSp3Download(sp3Properties.authorizedDownload());
  }

  @Produces
  @IfBuildProperty(name = "ouranos.parallel-processing.enabled", stringValue = "true")
  public ISp3Service getSp3ServiceParallel(ISp3FileDao sp3FileRepository, AuthorizedNewSp3Download authorizedNewSp3Download
      , Sp3DownloadAndStoreFactory sp3DownloadAndStoreFactory) {

    return new Sp3ParallelService(sp3FileRepository, authorizedNewSp3Download, sp3DownloadAndStoreFactory);
  }

  @Produces
  @DefaultBean
  public ISp3Service getSp3sequentialService(ISp3FileDao sp3FileRepository, ISp3InputStreamDao sp3InputStreamRepository
      , AuthorizedNewSp3Download authorizedNewSp3Download) {

    return new Sp3SequentialService(sp3FileRepository, sp3InputStreamRepository, authorizedNewSp3Download);
  }
}
