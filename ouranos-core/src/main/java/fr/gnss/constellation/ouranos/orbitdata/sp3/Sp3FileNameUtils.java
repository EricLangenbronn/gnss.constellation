package fr.gnss.constellation.ouranos.orbitdata.sp3;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3Const;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.orbitdata.OrbitDataUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sp3FileNameUtils {

  public static LocalDateTime getStartDateTime(Sp3FileName sp3FileName) {
    LocalDateTime dateTimeDebut = null;
    if (sp3FileName != null) {
      LocalDate dateDebut = Sp3Const.FIRST_EPOCH_RECORD.plusWeeks(sp3FileName.getGpsWeek());
      dateDebut = dateDebut.plusDays(sp3FileName.getDay());
      LocalTime timeDebut = LocalTime.of(0, 0);
      dateTimeDebut = LocalDateTime.of(dateDebut, timeDebut);
    }

    return dateTimeDebut;
  }

  public static LocalDateTime getEndDateTime(Sp3FileName sp3FileName) {
    LocalDateTime dateTimeFin = null;
    if (sp3FileName != null) {
      LocalDate dateDebut = Sp3Const.FIRST_EPOCH_RECORD.plusWeeks(sp3FileName.getGpsWeek());
      dateDebut = dateDebut.plusDays(sp3FileName.getDay());
      LocalTime timeFin = LocalTime.of(23, 59);
      dateTimeFin = LocalDateTime.of(dateDebut, timeFin);
    }

    return dateTimeFin;
  }

  public static Sp3FileName getSp3FileName(EphemerideType ephemerideType, LocalDateTime date, OrbitType orbitType) {
    int hour = ephemerideType.equals(EphemerideType.igu) ? date.getHour() : -1;
    // according to SP3FileName Day of week (Sun=0 --> Sat=6)
    int dayOfWeek = date.getDayOfWeek().getValue() == 7 ? 0 : date.getDayOfWeek().getValue();

    return new Sp3FileName(ephemerideType, OrbitDataUtils.getGpsWeek(date), dayOfWeek, hour, orbitType);
  }

  public static List<Sp3FileName> getAllSp3FileNameBetween2Date(EphemerideType ephemerideType
      , LocalDateTime dateDebut, LocalDateTime dateFin, OrbitType orbitType) {

    List<Sp3FileName> sp3FileBetween2Date = new ArrayList<>();

    for (LocalDateTime i = dateDebut; i.isBefore(dateFin); i = i.plusHours(23).plusMinutes(59).plusSeconds(59)) {
      sp3FileBetween2Date.add(Sp3FileNameUtils.getSp3FileName(ephemerideType, i, orbitType));
    }

    return sp3FileBetween2Date;
  }
}
