package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3Const;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

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
		LocalDateTime dateTimeDebut = null;
		if (sp3FileName != null) {
			LocalDate dateDebut = Sp3Const.FIRST_EPOCH_RECORD.plusWeeks(sp3FileName.getGpsWeek());
			dateDebut = dateDebut.plusDays(sp3FileName.getDay());
			LocalTime timeDebut = LocalTime.of(23, 59);
			dateTimeDebut = LocalDateTime.of(dateDebut, timeDebut);
		}

		return dateTimeDebut;
	}
}
