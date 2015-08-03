package fr.gnss.constellation.librairy.almanach.sp3;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3Parser;

public class Sp3FileReader {

	private File fl;
	private Sp3Parser parser;

	public Sp3FileReader(File p_file) throws Exception {
		fl = new File(p_file, "r");
		parser = new Sp3Parser(fl);
	}

	public Sp3FileReader(String p_fileName) throws Exception {
		fl = new File(p_fileName, "r");
		parser = new Sp3Parser(fl);
	}

	public Map<LocalDateTime, List<PositionAndClockRecord>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		return parser.getPositionAndClockRecord();
	}

}
