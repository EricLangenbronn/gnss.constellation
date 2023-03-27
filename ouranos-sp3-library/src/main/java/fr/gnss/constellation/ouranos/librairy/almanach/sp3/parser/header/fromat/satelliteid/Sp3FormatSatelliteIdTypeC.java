package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Sp3FormatSatelliteIdTypeC implements ISp3FormatSatelliteId {

  @Override
  public void parseSatelliteId(String line, Sp3Header sp3Header) {
    if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '+')) {
      throw new IllegalArgumentException(String.format("Ligne mal formatée, + attendu [line=%s]", line));
    }

    String satelliteIdInformation = line.substring(9, line.length());
    List<String> satelliteIds = Arrays.stream(satelliteIdInformation.split("(?<=\\G...)"))
        .map(String::trim)
        .filter(satelliteId -> !"0".equals(satelliteId))
        .toList();

    sp3Header.getSatId().addAll(satelliteIds);
  }

}
