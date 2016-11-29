package fr.gnss.constellation.ouranos.service.orbitdata;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3Const;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

public class OrbitDataUtils {

	public static long getGpsWeek(LocalDate date) {
		long gpsWeek = -1;

		gpsWeek = ChronoUnit.WEEKS.between(Sp3Const.FIRST_EPOCH_RECORD, date);

		return gpsWeek;
	}

	public static String getFileName(EphemerideType ephemerideType, LocalDate date, OrbitType orbitType) {
		String sp3FileName = "";

		sp3FileName = ephemerideType.toString() + "" + OrbitDataUtils.getGpsWeek(date) + ""
				+ date.getDayOfWeek().getValue() + "." + orbitType.toString() + ".Z";

		return sp3FileName;
	}

	public static Sp3FileName getSp3FileName(EphemerideType ephemerideType, LocalDate date, OrbitType orbitType) {
		Sp3FileName sp3FileName = new Sp3FileName(ephemerideType, OrbitDataUtils.getGpsWeek(date),
				date.getDayOfWeek().getValue(), orbitType);

		return sp3FileName;
	}

}
