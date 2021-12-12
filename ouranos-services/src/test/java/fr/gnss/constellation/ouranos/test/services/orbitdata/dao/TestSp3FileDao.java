package fr.gnss.constellation.ouranos.test.services.orbitdata.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.orbitdata.access.dao.Sp3FileDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSp3FileDao {
    // http://www.vogella.com/tutorials/Mockito/article.html

    @Test
    public void testGetFile() throws Exception {

        Sp3FileDao sp3FileDao = mock(Sp3FileDao.class);

        when(sp3FileDao.getFile("data", new Sp3FileName("igs22810.sp3"))).thenReturn(null);

        // :') OK mais je peux lui faire dire ce que je veux sans que le test soit
        // correcte pour autant ??
        assertNull(sp3FileDao.getFile("data", new Sp3FileName("igs12810.sp3")));

    }

}
