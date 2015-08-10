package fr.gnss.contellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3cCoreParser;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;

public class TestSp3cCoreParser {

	private static Sp3cCoreParser sp3cCP;

	@Before
	public void loadFile() throws IOException, TechnicalException {
		String sp3FileName = TestSp3cCoreParser.class.getResource(
				"/Sp3File/igs17720.sp3").getFile();
		FileReader sp3File = new FileReader(sp3FileName);
		sp3cCP = new Sp3cCoreParser(sp3File);
	}

	@Test
	public void TestFirstLine() throws TechnicalException {
		String line = sp3cCP.readLine();
		Assert.assertEquals("*  2013 12 22  0  0  0.00000000", line);
	}

	@Test
	public void TestPositionAndClockRecord() throws TechnicalException,
			BusinessException {
		List<Entry<LocalDateTime, List<PositionAndClockRecord>>> res = sp3cCP
				.getPositionAndClockRecord();
		Assert.assertNotNull(res);
		Assert.assertEquals(1, res.size());
		System.out.println(res);
	}
}
