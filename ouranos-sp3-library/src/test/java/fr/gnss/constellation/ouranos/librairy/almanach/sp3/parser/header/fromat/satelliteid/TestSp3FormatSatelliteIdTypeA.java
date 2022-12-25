package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSp3FormatSatelliteIdTypeA {

	private Sp3FormatSatelliteIdTypeA sp3FormatSatelliteIdTypeA = new Sp3FormatSatelliteIdTypeA();

	@Test
	public void whenLineContainsStaId_thenGetAllSatId() {
		Sp3Header sp3Header = new Sp3Header();
		String line = "+   29     1  3  4  5  6  7  8  9 10 11 13 14 15 16 17 18 19";

		sp3FormatSatelliteIdTypeA.parseSatelliteId(line, sp3Header);

		assertEquals(17, sp3Header.getSatId().size());
	}

	@Test
	public void whenLineContainsStaId_thenGetOnlyRealSatId() {
		Sp3Header sp3Header = new Sp3Header();
		String line = "+         20 21 22 23 24 25 26 27 28 29 30 31  0  0  0  0  0";

		sp3FormatSatelliteIdTypeA.parseSatelliteId(line, sp3Header);

		assertEquals(12, sp3Header.getSatId().size());
	}

}
