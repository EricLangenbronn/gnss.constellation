package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;

public class Sp3aCoreParser extends AbstractCoreParser implements
		Sp3ICoreParser {

	public Sp3aCoreParser(FileReader p_fr) throws TechnicalException {
		super(p_fr);
	}

	@Override
	public void initParser() throws TechnicalException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		/*
		 * List<PositionAndClockRecord> lstPositionAndClockRecord = new
		 * ArrayList<PositionAndClockRecord>();
		 * 
		 * // * 15 minutes * 60 secondes = 900 seconde int section = (int)
		 * (epochRecord.toSecondOfDay() / 900.0);
		 * 
		 * int nbSatelites = 32; for (int i = 0; i < nbSatelites; ++i) {
		 * 
		 * }
		 */
		return null;
	}

	@Override
	public List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord(LocalDateTime start,
			LocalDateTime end) throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
