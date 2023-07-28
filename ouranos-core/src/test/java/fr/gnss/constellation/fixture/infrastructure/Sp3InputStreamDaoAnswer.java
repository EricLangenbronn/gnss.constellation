package fr.gnss.constellation.fixture.infrastructure;

import fr.gnss.constellation.ouranos.common.compress.UnCompress;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class Sp3InputStreamDaoAnswer {

  private static final String sp3TestDirectory = "sp3File/";

  public static Answer<InputStream> downloadSp3FileAnswer() {
    return new Answer<>() {
      @Override
      public InputStream answer(InvocationOnMock invocation) throws Throwable {

        Sp3FileName sp3FileName = invocation.getArgument(0, Sp3FileName.class);
        boolean unCompressSp3File = invocation.getArgument(1, Boolean.class);

        if (unCompressSp3File) {
          return UnCompress.unCompressZFile(
              getClass().getClassLoader().getResourceAsStream(sp3TestDirectory + sp3FileName.getFileName(true)));
        } else {
          return getClass().getClassLoader().getResourceAsStream(sp3TestDirectory + sp3FileName.getFileName(true));
        }
      }
    };
  }

  public static Answer<Map<Sp3FileName, InputStream>> downloadSp3FilesAnswer() {
    return new Answer<>() {
      @Override
      public Map<Sp3FileName, InputStream> answer(InvocationOnMock invocation) throws Throwable {

        List<Sp3FileName> sp3FileNames = invocation.getArgument(0);
        boolean unCompressSp3File = invocation.getArgument(1, Boolean.class);

        Map<Sp3FileName, InputStream> sp3Files = new HashMap<>();
        for (Sp3FileName sp3FileName : sp3FileNames) {
          if (unCompressSp3File) {
            sp3Files.put(
                sp3FileName,
                UnCompress.unCompressZFile(
                    getClass().getClassLoader().getResourceAsStream(sp3TestDirectory + sp3FileName.getFileName(true))
                )
            );
          } else {
            sp3Files.put(
                sp3FileName,
                getClass().getClassLoader().getResourceAsStream(sp3TestDirectory + sp3FileName.getFileName(true))
            );
          }
        }

        return sp3Files;
      }
    };
  }
}
