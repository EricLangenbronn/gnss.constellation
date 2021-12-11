package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSp3FormatSecondLineTypeC {

    @Test
    public void testWhenParseSecondLine_thenMapDataOk() {
        String firstLine = "## 1772      0.00000000   900.00000000 56648 0.0000000000000";
        Sp3FormatSecondLineTypeC sp3FormatSecondLineTypeC = new Sp3FormatSecondLineTypeC();

        Sp3Header sp3Header = new Sp3Header();
        sp3FormatSecondLineTypeC.parseSecondLine(firstLine, sp3Header);

        assertEquals(1772, sp3Header.getGPSWeek());
        assertEquals(0.00000000f, sp3Header.getsSecondsOfWeek());
        assertEquals(900.00000000f, sp3Header.getEpochInterval());
        assertEquals(56648, sp3Header.getModJulDaySt());
        assertEquals(0.0000000000000f, sp3Header.getFractionalDay());
    }

}
