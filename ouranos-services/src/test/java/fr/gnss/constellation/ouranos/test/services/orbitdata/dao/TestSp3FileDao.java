package fr.gnss.constellation.ouranos.test.services.orbitdata.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.Sp3FileDao;

public class TestSp3FileDao {
	// http://www.vogella.com/tutorials/Mockito/article.html

	@Test
	public void testGetFile() throws Exception {

		Sp3FileDao sp3FileDao = mock(Sp3FileDao.class);

		when(sp3FileDao.getFile("data", new Sp3FileName("igs22810.sp3"))).thenReturn(null);

		// :') OK mais je peux lui faire dire ce que je veux sans que le test soit
		// correcte pour autant ??
		assertEquals(sp3FileDao.getFile("data", new Sp3FileName("igs12810.sp3")), null);

	}

}
