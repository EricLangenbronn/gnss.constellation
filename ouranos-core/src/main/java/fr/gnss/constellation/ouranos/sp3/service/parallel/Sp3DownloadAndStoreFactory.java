package fr.gnss.constellation.ouranos.sp3.service.parallel;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.EpochDirectory;
import fr.gnss.constellation.ouranos.sp3.infrastructure.Sp3InputStreamDao;
import fr.gnss.constellation.ouranos.sp3.persitence.Sp3FileDao;
import fr.gnss.constellation.ouranos.sp3.persitence.Sp3StorageDirectory;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class Sp3DownloadAndStoreFactory {

  private final Sp3StorageDirectory defaultDownloadSp3Directory;
  private final FtpServerName ftpServerName;
  private final EpochDirectory epochDirectory;

  public Sp3DownloadAndStoreTask getSp3DownloadAndStoreTask(Sp3FileName sp3FileName) {

    return new Sp3DownloadAndStoreTask(
        new Sp3InputStreamDao(ftpServerName, epochDirectory)
        , new Sp3FileDao(defaultDownloadSp3Directory)
        , sp3FileName
    );
  }
}
