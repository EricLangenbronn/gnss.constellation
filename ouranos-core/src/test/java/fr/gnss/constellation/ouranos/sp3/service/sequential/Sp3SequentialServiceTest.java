package fr.gnss.constellation.ouranos.sp3.service.sequential;


import fr.gnss.constellation.fixture.infrastructure.Sp3InputStreamDaoAnswer;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.sp3.Sp3InputStreamDao;
import fr.gnss.constellation.ouranos.sp3.persitence.Sp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Sp3SequentialServiceTest {

  private final Sp3FileDao sp3FileDao = Mockito.mock(Sp3FileDao.class);
  private final Sp3InputStreamDao sp3InputStreamDao = Mockito.mock(Sp3InputStreamDao.class);


  @Test
  public void whenAllowDownloadThenCallSp3InputStream() {
    Sp3SequentialService sp3SequentialService = new Sp3SequentialService(sp3FileDao, sp3InputStreamDao, new AuthorizedNewSp3Download(true));
    Sp3FileName sp3FileName = new Sp3FileName("igs12821.sp3.Z");
    Mockito.when(sp3InputStreamDao.downloadSp3File(sp3FileName, true)).thenAnswer(Sp3InputStreamDaoAnswer.downloadSp3FileAnswer());

    sp3SequentialService.downloadsAndStoresIfNotExist(List.of(sp3FileName));

    Mockito.verify(sp3InputStreamDao, Mockito.times(1)).downloadSp3File(sp3FileName, true);
  }

  @Test
  public void whenNotAllowDownloadThenCallSp3InputStream() {
    Sp3SequentialService sp3SequentialService =
        new Sp3SequentialService(sp3FileDao, sp3InputStreamDao, new AuthorizedNewSp3Download(false));
    Sp3FileName sp3FileName = new Sp3FileName("igs12821.sp3.Z");
    Mockito.when(sp3InputStreamDao.downloadSp3File(sp3FileName, true)).thenAnswer(Sp3InputStreamDaoAnswer.downloadSp3FileAnswer());

    sp3SequentialService.downloadsAndStoresIfNotExist(List.of(sp3FileName));

    Mockito.verify(sp3InputStreamDao, Mockito.times(0)).downloadSp3File(sp3FileName, true);
  }
}
