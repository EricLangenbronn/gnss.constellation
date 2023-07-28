package fr.gnss.constellation.fixture.infrastructure;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.sp3.ISp3InputStreamDao;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sp3InputStreamDaoFixture implements ISp3InputStreamDao {

  private String sp3Path = "sp3File/";

  @Override
  public InputStream downloadSp3File(Sp3FileName sp3FileName, boolean unCompressSp3File) {
    return getClass().getClassLoader().getResourceAsStream(sp3Path + sp3FileName);
  }

  @Override
  public Map<Sp3FileName, InputStream> downloadSp3Files(List<Sp3FileName> sp3FileNames, boolean unCompressSp3File) {
    Map<Sp3FileName, InputStream> downloadSp3Files = new HashMap<>();

    for (Sp3FileName sp3FileName : sp3FileNames) {
      downloadSp3Files.put(sp3FileName, getClass().getClassLoader().getResourceAsStream(sp3Path + "/" + sp3FileName));
    }

    return downloadSp3Files;
  }
}
