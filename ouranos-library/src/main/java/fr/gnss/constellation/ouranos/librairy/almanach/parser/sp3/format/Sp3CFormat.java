package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;

public class Sp3CFormat {

	public static Sp3SateliteInformation formatSatelitePosition(String line) throws BusinessException {
		if (line.charAt(0) != 'P') {
			String message = "Ligne mal formatée, P attendu [line=" + line + "]";
			throw new BusinessException(message);
		}

		String vehicleId = line.substring(1, 4);
		double x = Double.parseDouble(line.substring(4, 18).trim());
		double y = Double.parseDouble(line.substring(18, 32).trim());
		double z = Double.parseDouble(line.substring(32, 46).trim());
		double clock = Double.parseDouble(line.substring(46, 60).trim());

		// the position values are in km and have to be converted to m
		Sp3SateliteInformation res = new Sp3SateliteInformation(vehicleId, x * 1000, y * 1000, z * 1000);

		return res;
	}

	public static LocalDateTime formatEpochHeader(String line) throws BusinessException {
		Objects.requireNonNull(line);

		if (line.charAt(0) != '*') {
			String message = "Ligne mal formaté, * attendu [line=" + line + "]";
			throw new BusinessException(message);
		}

		String year = line.substring(3, 7);
		String month = line.substring(8, 10).replaceFirst(" ", "0");
		String day = line.substring(11, 13).replaceFirst(" ", "0");
		String hour = line.substring(14, 16).replaceFirst(" ", "0");
		String minute = line.substring(17, 19).replaceFirst(" ", "0");
		String sec = line.substring(20, 31).replaceFirst(" ", "0");

		String dateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + sec;

		LocalDateTime clock = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);

		return clock;
	}
}
