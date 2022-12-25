package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public class TestSp3FormatSatelliteIdTypeC {

	private Sp3FormatSatelliteIdTypeC sp3FormatSatelliteIdTypeC = new Sp3FormatSatelliteIdTypeC();

	@Test
	public void whenLineContainsStaId_thenGetAllSatId() {
		Sp3Header sp3Header = new Sp3Header();
		String line = "+   32   G01G02G03G04G05G06G07G08G09G10G11G12G13G14G15G16G17";

		sp3FormatSatelliteIdTypeC.parseSatelliteId(line, sp3Header);

		assertEquals(17, sp3Header.getSatId().size());
	}

	@Test
	public void whenLineContainsStaId_thenGetOnlyRealSatId() {
		Sp3Header sp3Header = new Sp3Header();
		String line = "+        G18G19G20G21G22G23G24G25G26G27G28G29G30G31G32  0  0";

		sp3FormatSatelliteIdTypeC.parseSatelliteId(line, sp3Header);

		assertEquals(15, sp3Header.getSatId().size());
	}

}
