package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataDownloadService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-dao-test.xml", "/moduleTest/ouranos-services-test.xml" })
public class ITOrbitsDataDownloadService {

	@Autowired
	private IOrbitsDataDownloadService orbitsDataDownloadService;

	@Test
	public void testDownloadAndGetFileForPeriodOneDay1() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(1, sp3File.size());
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDay2() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(1, sp3File.size());
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayBeforeMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(1, sp3File.size());
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayAfterMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(1, sp3File.size());
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayNoData() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(1, sp3File.size());
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayNoDataMultiDay() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-24T15:00", DateTimeFormatter.ISO_DATE_TIME);

		List<File> sp3File = orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, EphemerideType.igs,
				OrbitType.sp3);
		assertNotNull(sp3File);
		assertEquals(3, sp3File.size());
	}

	public void setOrbitsDataDownloadService(IOrbitsDataDownloadService orbitsDataDownloadService) {
		this.orbitsDataDownloadService = orbitsDataDownloadService;
	}

}
