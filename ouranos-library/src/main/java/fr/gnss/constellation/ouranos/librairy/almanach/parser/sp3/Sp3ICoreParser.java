package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;

public interface Sp3ICoreParser {
	
	public static int NB_MIN_MEASURE = 15;

	Entry<LocalDateTime, List<Satelite>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException;
	
	List<Entry<LocalDateTime, List<Satelite>>> getPeriodOfPosition(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException;
}
