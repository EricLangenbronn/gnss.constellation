package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

	public List<PositionAndClockRecord> getPositionAndClockRecord(
			LocalTime epochRecord) throws TechnicalException {
		List<PositionAndClockRecord> lstPositionAndClockRecord = new ArrayList<PositionAndClockRecord>();

		// * 15 minutes * 60 secondes = 900 seconde
		int section = (int) (epochRecord.toSecondOfDay() / 900.0);

		int nbSatelites = 32;
		for (int i = 0; i < nbSatelites; ++i) {
			
		}

		return lstPositionAndClockRecord;
	}
}
