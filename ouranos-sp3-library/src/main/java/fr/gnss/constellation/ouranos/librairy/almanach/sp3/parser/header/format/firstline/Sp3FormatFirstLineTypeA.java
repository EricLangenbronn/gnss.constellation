package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sp3FormatFirstLineTypeA extends AbstractSp3FormatFirstLine {

    @Override
    public void parseFirstLine(String line, Sp3Header sp3Header) {

        if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '#')) {
            throw new RuntimeException(String.format("Ligne mal formatée, # attendu [line=%s]", line));
        }

        sp3Header.setVersionSymbol(line.substring(0, 2));

        String year = line.substring(3, 7);
        String month = line.substring(8, 10).replaceFirst(" ", "0");
        String day = line.substring(11, 13).replaceFirst(" ", "0");
        String hour = line.substring(14, 16).replaceFirst(" ", "0");
        String minute = line.substring(17, 19).replaceFirst(" ", "0");
        String sec = line.substring(20, 31).replaceFirst(" ", "0");

        String dateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + sec;
        sp3Header.setStartTime(LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME));

        sp3Header.setNumberOfEpochs(Integer.parseInt(line.substring(32, 39).trim()));
        sp3Header.setDataUsed(line.substring(40, 45).trim());
        sp3Header.setCoordinateSys(line.substring(46, 51));
        sp3Header.setOrbitType(line.substring(52, 55));
        sp3Header.setAgency(line.substring(56, 60).trim());
    }

}
