package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;

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
	public Entry<LocalDateTime, List<Satelite>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entry<LocalDateTime, List<Satelite>>> getPeriodOfPosition(LocalDateTime start,
			LocalDateTime end) throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
