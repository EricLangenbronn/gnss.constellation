package fr.gnss.constellation.ouranos.persistence.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3Const;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrbitDataUtils {

    public static long getGpsWeek(LocalDateTime date) {
        long gpsWeek = -1;

        gpsWeek = ChronoUnit.WEEKS.between(Sp3Const.FIRST_EPOCH_RECORD, date);

        return gpsWeek;
    }

    public static Sp3FileName getSp3FileName(EphemerideType ephemerideType, LocalDateTime date, OrbitType orbitType) {
        int hour = ephemerideType.equals(EphemerideType.igu) ? date.getHour() : -1;
        // according to SP3FileName Day of week (Sun=0 --> Sat=6)
        int dayOfWeek = date.getDayOfWeek().getValue() == 7 ? 0 : date.getDayOfWeek().getValue();

        return new Sp3FileName(ephemerideType, OrbitDataUtils.getGpsWeek(date), dayOfWeek, hour,
                orbitType);
    }

    public static List<Sp3FileName> getAllSp3FileNameBetween2Date(EphemerideType ephemerideType
            , LocalDateTime dateDebut, LocalDateTime dateFin, OrbitType orbitType) {

        List<Sp3FileName> sp3FileBetween2Date = new ArrayList<>();

        for (LocalDateTime i = dateDebut; i.compareTo(dateFin) < 0; i = i.plusHours(23).plusMinutes(59).plusSeconds(59)) {
            sp3FileBetween2Date.add(OrbitDataUtils.getSp3FileName(ephemerideType, i, orbitType));
        }

        return sp3FileBetween2Date;
    }

}
