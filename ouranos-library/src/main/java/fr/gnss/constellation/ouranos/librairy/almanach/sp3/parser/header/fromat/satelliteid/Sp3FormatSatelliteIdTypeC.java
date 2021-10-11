package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public class Sp3FormatSatelliteIdTypeC extends AbstractSp3FormatSatelliteId {

	@Override
	public void parseSatelliteId(String line, Sp3Header sp3Header) {
		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '+')) {
			String message = "Ligne mal formatée, + attendu [line=" + line + "]";
			throw new RuntimeException(message);
		}

		String satelliteIdInformation = line.substring(9, line.length());
		List<String> satelliteIds = Arrays.asList(satelliteIdInformation.split("(?<=\\G...)")).stream()
				.map(String::trim)
				.filter(satelliteId -> !"0".equals(satelliteId))
				.collect(Collectors.toList());
		
		sp3Header.getSatId().addAll(satelliteIds);
	}

}
