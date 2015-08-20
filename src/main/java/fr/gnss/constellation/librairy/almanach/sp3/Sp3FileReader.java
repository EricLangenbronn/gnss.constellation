package fr.gnss.constellation.librairy.almanach.sp3;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3Parser;

public class Sp3FileReader {

	private File fl;
	private Sp3Parser parser;

	public Sp3FileReader(File p_file) throws TechnicalException, BusinessException {
		fl = new File(p_file, "r");
		parser = new Sp3Parser(fl);
	}

	public Sp3FileReader(String p_fileName) throws TechnicalException, BusinessException {
		fl = new File(p_fileName);
		parser = new Sp3Parser(fl);
	}

	public List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		return parser.getPositionAndClockRecord();
	}
	
	public List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getPositionAndClockRecord(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {
		return parser.getPositionAndClockRecord(start, end);
	}

}
