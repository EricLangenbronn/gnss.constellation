package fr.gnss.constellation.ouranos.test.services;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.orbitdata.OrbitDataUtils;

public class TestOrbitDataUtils {

	@Test
	public void testOrbitDataUtilsGetGpsWeek() throws Exception {
		// 4 aout 2008 // 1282

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("04/08/2004");

		LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		long res = OrbitDataUtils.getGpsWeek(localDate);
		assertEquals(1282, res);

	}

	@Test
	public void testOrbitDataUtilsGetSp3FileName() throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("04/08/2004");

		LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		Sp3FileName res = OrbitDataUtils.getSp3FileName(EphemerideType.igs, localDate, OrbitType.sp3);

		Sp3FileName expected = new Sp3FileName(EphemerideType.igs, 1282, 3, -1, OrbitType.sp3);
		assertEquals(expected, res);
	}

	@Test
	public void testOrbitDataUtilsGetSp3FileNameSunday() throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("07/05/2017");

		LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		Sp3FileName res = OrbitDataUtils.getSp3FileName(EphemerideType.igs, localDate, OrbitType.sp3);

		Sp3FileName expected = new Sp3FileName(EphemerideType.igs, 1948, 0, -1, OrbitType.sp3);
		assertEquals(expected, res);
	}

	@Test
	public void testOrbitDataUtilsGetAllSp3FileNameBetween2Date2Day() throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDebut = formatter.parse("04/08/2004");
		Date dateFin = formatter.parse("06/08/2004");

		LocalDateTime localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		List<Sp3FileName> res = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, localDateDebut,
				localDateFin, OrbitType.sp3);

		assertEquals(3, res.size());
	}
	
	@Test
	public void testOrbitDataUtilsGetAllSp3FileNameBetween2Date1Day() throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDebut = formatter.parse("03/04/2004");
		Date dateFin = formatter.parse("04/04/2004");

		LocalDateTime localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		List<Sp3FileName> res = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, localDateDebut,
				localDateFin, OrbitType.sp3);

		assertEquals(2, res.size());
	}

}
