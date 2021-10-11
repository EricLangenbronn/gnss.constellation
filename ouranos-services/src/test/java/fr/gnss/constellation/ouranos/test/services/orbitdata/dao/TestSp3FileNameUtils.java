package fr.gnss.constellation.ouranos.test.services.orbitdata.dao;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.Sp3FileNameUtils;

public class TestSp3FileNameUtils {

	@Test
	public void testSp3FileNameNull() throws Exception {
		LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(null);
		assertNull(dateDebut);
	}

	@Test
	public void testSp3FileNameDateTimeDebutDimanche() throws Exception {
		Sp3FileName sp3FileName = new Sp3FileName("igs17720.sp3");
		LocalDateTime expectedDate = LocalDateTime.of(2013, 12, 22, 0, 0, 0);

		LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);

		assertEquals(expectedDate, dateDebut);
	}

	@Test
	public void testSp3FileNameDateTimeDebutOtherDay() throws Exception {
		Sp3FileName sp3FileName = new Sp3FileName("igs18254.sp3");
		LocalDateTime expectedDate = LocalDateTime.of(2015, 1, 1, 0, 0, 0);

		LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);

		assertEquals(expectedDate, dateDebut);
	}

	@Test
	public void testSp3FileNameDateTimeEndDimanche() throws Exception {
		Sp3FileName sp3FileName = new Sp3FileName("igs17720.sp3");
		LocalDateTime expectedDate = LocalDateTime.of(2013, 12, 22, 23, 59, 0);

		LocalDateTime dateDebut = Sp3FileNameUtils.getEndDateTime(sp3FileName);

		assertEquals(expectedDate, dateDebut);
	}

	@Test
	public void testSp3FileNameDateTimeEndOtherDay() throws Exception {
		Sp3FileName sp3FileName = new Sp3FileName("igs18254.sp3");
		LocalDateTime expectedDate = LocalDateTime.of(2015, 1, 1, 23, 59, 0);

		LocalDateTime dateDebut = Sp3FileNameUtils.getEndDateTime(sp3FileName);

		assertEquals(expectedDate, dateDebut);
	}
}
