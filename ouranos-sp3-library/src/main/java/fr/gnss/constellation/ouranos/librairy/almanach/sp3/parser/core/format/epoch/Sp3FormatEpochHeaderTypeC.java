package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public final class Sp3FormatEpochHeaderTypeC implements ISp3FormatEpochHeader {

  @Override
  public LocalDateTime parseEpochHeader(String line) {
    Objects.requireNonNull(line);

    if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != '*')) {
      throw new RuntimeException(String.format("Ligne mal formaté, * attendu [line=%s]", line));
    }

    String year = line.substring(3, 7);
    String month = line.substring(8, 10).replaceFirst(" ", "0");
    String day = line.substring(11, 13).replaceFirst(" ", "0");
    String hour = line.substring(14, 16).replaceFirst(" ", "0");
    String minute = line.substring(17, 19).replaceFirst(" ", "0");
    String sec = line.substring(20, 31).replaceFirst(" ", "0");

    String dateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + sec;

    return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
  }

}
