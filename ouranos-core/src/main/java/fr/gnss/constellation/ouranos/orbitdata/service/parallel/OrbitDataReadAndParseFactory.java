package fr.gnss.constellation.ouranos.orbitdata.service.parallel;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.sp3.EpochDirectory;
import fr.gnss.constellation.ouranos.sp3.persitence.Sp3FileDao;
import fr.gnss.constellation.ouranos.sp3.persitence.Sp3StorageDirectory;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import fr.gnss.constellation.ouranos.sp3.service.parallel.Sp3DownloadAndStoreFactory;
import fr.gnss.constellation.ouranos.sp3.service.parallel.Sp3ParallelService;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class OrbitDataReadAndParseFactory {

  private final Sp3StorageDirectory defaultDownloadSp3Directory;
  private final FtpServerName ftpServerName;
  private final EpochDirectory epochDirectory;
  private final AuthorizedNewSp3Download authorizedNewSp3Download;


  public OrbitDataReadAndParseTask getOrbitDataReadAndParseTask(Sp3FileName sp3FileName, LocalDateTime start, LocalDateTime end) {

    return new OrbitDataReadAndParseTask(
        new Sp3ParallelService(new Sp3FileDao(defaultDownloadSp3Directory), authorizedNewSp3Download
            , new Sp3DownloadAndStoreFactory(defaultDownloadSp3Directory, ftpServerName, epochDirectory))
        , sp3FileName
        , start
        , end
    );
  }

}
