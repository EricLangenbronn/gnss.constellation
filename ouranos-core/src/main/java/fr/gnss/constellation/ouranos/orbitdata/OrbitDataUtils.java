package fr.gnss.constellation.ouranos.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3Const;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OrbitDataUtils {

  public static long getGpsWeek(LocalDateTime date) {
    long gpsWeek = -1;

    gpsWeek = ChronoUnit.WEEKS.between(Sp3Const.FIRST_EPOCH_RECORD, date);

    return gpsWeek;
  }
}
