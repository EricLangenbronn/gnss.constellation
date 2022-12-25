package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSp3FormatFirstLineTypeC {

    @Test
    public void testWhenParseFirstLine_thenMapDataOk() {
        String firstLine = "#cP2013 12 22  0  0  0.00000000      96 ORBIT IGb08 HLM  IGS";
        Sp3FormatFirstLineTypeC sp3FormatFirstLineTypeC = new Sp3FormatFirstLineTypeC();

        Sp3Header sp3Header = new Sp3Header();
        sp3FormatFirstLineTypeC.parseFirstLine(firstLine, sp3Header);


        assertEquals(2013, sp3Header.getStartTime().getYear());
        assertEquals(Month.DECEMBER, sp3Header.getStartTime().getMonth());
        assertEquals(22, sp3Header.getStartTime().getDayOfMonth());
        assertEquals("ORBIT", sp3Header.getDataUsed());
        assertEquals("IGb08", sp3Header.getCoordinateSys());
        assertEquals("HLM", sp3Header.getOrbitType());
        assertEquals("IGS", sp3Header.getAgency());
    }

}
