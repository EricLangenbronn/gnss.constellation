package fr.gnss.constellation.ouranos.test.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-dao-test.xml", "/moduleTest/ouranos-services-test.xml" })
public class TestValidationService {

	@Autowired
	private IOrbitsDataService orbitsDataService;

	@Test
	public void testIsDataForPeriodSmall() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = orbitsDataService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodLarge() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = orbitsDataService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodBeforeMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = orbitsDataService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodAfterMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = orbitsDataService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodNotData() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = orbitsDataService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}

	public void setValidationService(IOrbitsDataService validationService) {
		this.orbitsDataService = validationService;
	}

}
