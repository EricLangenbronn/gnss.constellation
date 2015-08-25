package fr.gnss.contellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map.Entry;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3cCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;

public class TestSp3cCoreParser {

	private static Sp3cCoreParser sp3cCP;

	@Before
	public void loadFile() throws IOException, TechnicalException {
		String sp3FileName = TestSp3cCoreParser.class.getResource("/Sp3File/igs17720.sp3").getFile();
		FileReader sp3File = new FileReader(sp3FileName);
		sp3cCP = new Sp3cCoreParser(sp3File, LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME),
				32);
	}

	@Test
	public void TestFirstLine() throws TechnicalException {
		String line = sp3cCP.readLine();
		assertEquals("*  2013 12 22  0  0  0.00000000", line);
	}

	@Test
	public void TestPositionAndClockRecord() throws TechnicalException, BusinessException {
		List<Entry<LocalDateTime, List<Satelite>>> res = sp3cCP.getPeriodOfPosition(
				LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME),
				LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME));
		assertNotNull(res);
		assertEquals(95, res.size());
	}
}
