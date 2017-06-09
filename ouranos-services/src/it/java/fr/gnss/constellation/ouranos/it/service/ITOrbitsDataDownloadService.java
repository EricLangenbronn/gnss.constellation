package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.IPropertiesService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataDownloadService;
import fr.gnss.constellation.ouranos.service.orbitdata.OrbitDataUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-dao-test.xml", "/moduleTest/ouranos-services-test.xml" })
public class ITOrbitsDataDownloadService {

	@Autowired
	private IOrbitsDataDownloadService orbitsDataDownloadService;

	@Autowired
	private IPropertiesService propertiesService;

	@After
	public void deleteAllDownloadFile() throws IOException {
		FileUtils.deleteDirectory(new File(this.propertiesService.getString("repertoire.download.sp3")));
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDay1() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDay2() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayBeforeMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayAfterMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayNoData() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	@Test
	public void testDownloadAndGetFileForPeriodOneDayNoDataMultiDay() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-24T15:00", DateTimeFormatter.ISO_DATE_TIME);
		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs,
				start, end, OrbitType.sp3);

		this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

		for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
			File sp3DownloadFile = Paths
					.get(this.propertiesService.getString("repertoire.sp3"), sp3FileName.getFileName(false)).toFile();
			assertTrue(sp3DownloadFile.exists());
		}
	}

	public void setOrbitsDataDownloadService(IOrbitsDataDownloadService orbitsDataDownloadService) {
		this.orbitsDataDownloadService = orbitsDataDownloadService;
	}

}
