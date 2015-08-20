package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;

public interface Sp3ICoreParser {
	
	public static int NB_MIN_MEASURE = 15;

	List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException;
	
	List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException;
}
