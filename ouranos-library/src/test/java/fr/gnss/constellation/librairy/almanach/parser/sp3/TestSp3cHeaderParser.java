package fr.gnss.constellation.librairy.almanach.parser.sp3;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.c.Sp3cHeaderParser;

public class TestSp3cHeaderParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSp3cHeaderParser.class);

	private static Sp3cHeaderParser sp3cHP;

	@Before
	public void loadFile() throws IOException {
		String sp3FileName = TestSp3cHeaderParser.class.getResource("/Sp3File/igs17720.sp3").getFile();
		RandomAccessFile sp3File = new RandomAccessFile(sp3FileName, "r");
		sp3cHP = new Sp3cHeaderParser(sp3File);
	}

	@After
	public void closeFile() {
		this.sp3cHP.close();
	}

	@Test
	public void TestVersionSymbol() throws TechnicalException {
		String res = sp3cHP.getVersionSymbol();
		assertEquals("#c", res);
	}

	@Test
	public void TestStartDateTime() throws TechnicalException {
		LocalDateTime res = sp3cHP.getStartEpochRecord();
		LOGGER.info("res : " + res);
	}

	@Test
	public void TestNumberOfEpoch() throws TechnicalException {
		int res = sp3cHP.getNumberOfEpoch();
		assertEquals(96, res);
	}

	@Test
	public void TestDataUsed() throws TechnicalException {
		String res = sp3cHP.getDataUsed();
		assertEquals("ORBIT", res);
	}

	@Test
	public void TestCoordinateSystem() throws TechnicalException {
		String res = sp3cHP.getCoordinateSystem();
		assertEquals("IGb08", res);
	}

	@Test
	public void TestOrbitType() throws TechnicalException {
		String res = sp3cHP.getOrbitType();
		assertEquals("HLM", res);
	}

	@Test
	public void TestAgency() throws TechnicalException {
		String res = sp3cHP.getAgency();
		assertEquals("IGS", res);
	}

	@Test
	public void TestGPSWeek() throws TechnicalException {
		int res = sp3cHP.getGPSWeek();
		assertEquals(1772, res);
	}

	@Test
	public void TestSecondsOfWeek() throws TechnicalException {
		double res = sp3cHP.getSecondsOfWeek();
		assertEquals(0.00000000, res, 0.000);
	}

	@Test
	public void TestEpochInterval() throws TechnicalException {
		double res = sp3cHP.getEpochInterval();
		assertEquals(900.00000000, res, 0.000);
	}

	@Test
	public void TestModJulDaySt() throws TechnicalException {
		int res = sp3cHP.getModJulDaySt();
		assertEquals(56648, res);
	}

	@Test
	public void TestFractionalDay() throws TechnicalException {
		double res = sp3cHP.getFractionalDay();
		assertEquals(0.0000000000000, res, 0.000);
	}

	@Test
	public void TestNumber0fSats() throws TechnicalException {
		int res = sp3cHP.getNumber0fSats();
		assertEquals(32, res);
	}

	@Test
	public void TestSatId() throws TechnicalException, BusinessException {
		String resExpected = "G01G02G03G04G05G06G07G08G09G10G11G12G13G14G15G16G17G18G19G20G21G22G23G24G25G26G27G28G29G30G31G32";
		String[] res = sp3cHP.getSatId();
		String resStr = "";
		for (int i = 0; i < res.length; ++i) {
			resStr += res[i];
		}

		assertEquals(resStr, resExpected);
	}

	@Test
	public void TestSatAccuracy() throws TechnicalException, BusinessException {
		int[] res = sp3cHP.getSatAccuracy();

		for (int i = 0; i < res.length; ++i) {

			if (i == 29) {
				assertEquals(0, res[i]);
			} else {
				assertEquals(2, res[i]);
			}
		}
	}

	@Test
	public void TestFileType() throws TechnicalException {
		String res = sp3cHP.getFileType();
		assertEquals("G", res);
	}

	@Test
	public void TestTimeSystem() throws TechnicalException {
		String res = sp3cHP.getTimeSystem();
		assertEquals("GPS", res);
	}
}
