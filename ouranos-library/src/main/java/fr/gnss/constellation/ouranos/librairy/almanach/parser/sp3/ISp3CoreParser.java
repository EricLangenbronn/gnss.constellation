package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;

public interface ISp3CoreParser {

	public static int NB_MIN_MEASURE = 15;

	void close();

	Entry<LocalDateTime, List<Sp3SateliteInformation>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException;

	List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getPeriodOfPosition(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException;
}
