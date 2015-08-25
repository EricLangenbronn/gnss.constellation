package fr.gnss.contellation.ouranos.services;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.gnss.constellation.ouranos.services.ValidationService;

public class TestValidationService {

	@Test
	public void testIsDataForPeriodSmall() throws Exception {
		ValidationService validService = new ValidationService();

		LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodLarge() throws Exception {
		ValidationService validService = new ValidationService();

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodBeforeMidnight() throws Exception {
		ValidationService validService = new ValidationService();

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validService.isDataForPeriod(start, end);
		assertNotNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodAfterMidnight() throws Exception {
		ValidationService validService = new ValidationService();

		LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);

		// En java 24:00 interdit, on prend donc 00:00 du jour suivant
		LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}

	@Test
	public void testIsDataForPeriodNotData() throws Exception {
		ValidationService validService = new ValidationService();

		LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

		File sp3File = validService.isDataForPeriod(start, end);
		assertNull(sp3File);
	}
}
