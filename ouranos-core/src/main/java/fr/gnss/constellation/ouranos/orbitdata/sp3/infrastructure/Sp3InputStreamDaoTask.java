package fr.gnss.constellation.ouranos.orbitdata.sp3.infrastructure;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Sp3InputStreamDaoTask implements Callable<InputStream> {

  private final ISp3InputStreamDao sp3InputStreamDao;
  private final Sp3FileName sp3FileName;
  private final boolean unCompressSp3File;

  public Sp3InputStreamDaoTask(ISp3InputStreamDao sp3InputStreamDao, Sp3FileName sp3FileName, boolean unCompressSp3File) {
    Objects.requireNonNull(sp3InputStreamDao);
    Objects.requireNonNull(sp3FileName);

    this.sp3InputStreamDao = sp3InputStreamDao;
    this.sp3FileName = sp3FileName;
    this.unCompressSp3File = unCompressSp3File;
  }

  @Override
  public InputStream call() throws Exception {

    try {
      return sp3InputStreamDao.downloadSp3File(this.sp3FileName, this.unCompressSp3File);
    } catch (Exception e) {
      log.error(String.format("Impossible de télécharger le fichier : " + sp3FileName), e);
    }

    return null;
  }

}
