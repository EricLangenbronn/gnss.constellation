package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sp3FormatConst {
	
	public static LocalDate firstEpochRecord;
	public int sampleInterval = 15;
	
	static {
		 firstEpochRecord = LocalDate.parse("1980-01-06", DateTimeFormatter.ISO_DATE);
	}
}
