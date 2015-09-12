package fr.gnss.contellation.ouranos.services;

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

import fr.gnss.constellation.ouranos.services.OuranosValidationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/ouranos-dao-test.xml", "/ouranos-services-test.xml" })
public class TestValidationService {

	@Autowired
	private OuranosValidationService validationService;

	@Test
	public void testIsDataForPeriodSmall() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validationService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodLarge() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validationService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodBeforeMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validationService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodAfterMidnight() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validationService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodNotData() throws Exception {

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validationService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}

	public void setValidationService(OuranosValidationService validationService) {
		this.validationService = validationService;
	}

}
