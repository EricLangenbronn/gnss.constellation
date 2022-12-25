package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import org.apache.commons.lang3.StringUtils;

public class Sp3FormatSecondLineTypeA extends AbstractSp3FormatSecondLine {

    @Override
    public void parseSecondLine(String line, Sp3Header sp3Header) {

        if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '#' && line.charAt(1) != '#')) {
            throw new RuntimeException(String.format("Ligne mal formatée, ## attendu [line=%s]", line));
        }

        sp3Header.setGPSWeek(Integer.parseInt(line.substring(3, 7)));
        sp3Header.setsSecondsOfWeek(Float.parseFloat(line.substring(8, 23).trim()));
        sp3Header.setEpochInterval(Float.parseFloat(line.substring(24, 38).trim()));
        sp3Header.setModJulDaySt(Integer.parseInt(line.substring(39, 44)));
        sp3Header.setFractionalDay(Float.parseFloat(line.substring(45, 60).trim()));
    }

}
