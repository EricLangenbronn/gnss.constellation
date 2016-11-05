package fr.gnss.constellation.librairy.almanach.sp3;

import java.io.File;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;

public class TestSp3Parser {

	@Test
	public void testChargementParser() throws Exception {
		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		File sp3File = new File(sp3FileName);
		Sp3FileParser sp3Parser = new Sp3FileParser(sp3File);
		assertNotNull(sp3Parser);
	}
}
