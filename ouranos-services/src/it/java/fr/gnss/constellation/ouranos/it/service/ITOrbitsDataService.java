package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/moduleTest/ouranos-services-test.xml" })
public class ITOrbitsDataService {

	@Autowired
	private IOrbitsDataService orbitsDataService;

	@Test
	public void testReadDatasForPeriodStartAfterEndBefore() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

		List<File> files = new ArrayList<>();
		files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
				EphemerideType.igs, OrbitType.sp3);

		assertNotNull(datas);
		assertEquals(21, datas.size());

	}

	@Test
	public void testReadDatasForPeriodStartEqualsEndEquals() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		List<File> files = new ArrayList<>();
		files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
				EphemerideType.igs, OrbitType.sp3);

		assertNotNull(datas);
		assertEquals(96, datas.size());

	}

	@Test
	public void testReadDatasForPeriodStartEqualsEndBefore() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:45", DateTimeFormatter.ISO_DATE_TIME);

		List<File> files = new ArrayList<>();
		files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
				EphemerideType.igs, OrbitType.sp3);

		assertNotNull(datas);
		assertEquals(64, datas.size());

	}

	@Test
	public void testReadDatasForPeriodStartAfterEndEquals() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		List<File> files = new ArrayList<>();
		files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
				EphemerideType.igs, OrbitType.sp3);

		assertNotNull(datas);
		assertEquals(56, datas.size());

	}

	public void setOrbitsDataService(IOrbitsDataService orbitsDataService) {
		this.orbitsDataService = orbitsDataService;
	}

}
