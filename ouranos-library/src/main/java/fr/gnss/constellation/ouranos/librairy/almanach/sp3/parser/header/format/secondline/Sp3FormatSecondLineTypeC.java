package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public class Sp3FormatSecondLineTypeC extends AbstractSp3FormatSecondLine {

	@Override
	public void parseSecondLine(String line, Sp3Header sp3Header) {

		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '#' && line.charAt(1) != '#')) {
			String message = "Ligne mal formatée, ## attendu [line=" + line + "]";
			throw new RuntimeException(message);
		}

		sp3Header.setGPSWeek(line.substring(3, 7));
		sp3Header.setsSecondsOfWeek(line.substring(8, 23).trim());
		sp3Header.setEpochInterval(line.substring(24, 38).trim());
		sp3Header.setModJulDaySt(line.substring(39, 44));
		sp3Header.setFractionalDay(line.substring(45, 60).trim());
	}

}
