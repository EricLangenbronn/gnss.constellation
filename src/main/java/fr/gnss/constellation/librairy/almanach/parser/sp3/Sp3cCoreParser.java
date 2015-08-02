package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;

public class Sp3cCoreParser extends AbstractCoreParser implements
		Sp3ICoreParser {

	private static final long END_HEADER = 1342;

	public Sp3cCoreParser(FileReader p_fr) throws TechnicalException {
		super(p_fr);
	}

	@Override
	public void initParser() throws TechnicalException {
		try {
			// On se place au début du core du fichier
			sp3Buffer.skip(END_HEADER);
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}
	}

	private PositionAndClockRecord splitLine(String line)
			throws BusinessException {
		Objects.requireNonNull(line);

		if (line.charAt(0) != 'P') {
			String message = "";
			throw new BusinessException(line);
		}

		String vehicleId = line.substring(1, 4);
		double x = Double.parseDouble(line.substring(4, 18).trim());
		double y = Double.parseDouble(line.substring(18, 32).trim());
		double z = Double.parseDouble(line.substring(32, 46).trim());
		double clock = Double.parseDouble(line.substring(46, 60).trim());

		PositionAndClockRecord res = new PositionAndClockRecord(vehicleId, x,
				y, z);

		return res;
	}
	
	public LocalDateTime splitClockLine(String line) throws BusinessException {
		Objects.requireNonNull(line);

		if (line.charAt(0) != '*') {
			String message = "";
			throw new BusinessException(line);
		}
		
		String year = line.substring(3, 7);
		String month = line.substring(8, 10).replaceFirst(" ", "0");
		String day = line.substring(11, 13).replaceFirst(" ", "0");
		String hour = line.substring(14, 16).replaceFirst(" ", "0");
		String minute = line.substring(17, 19).replaceFirst(" ", "0");
		String sec = line.substring(20, 31).replaceFirst(" ", "0");
		
		String dateTime = year + "-" + month + "-" + day + "T" + hour + ":"
				+ minute + ":" + sec;
		
		LocalDateTime clock = LocalDateTime.parse(dateTime,
				DateTimeFormatter.ISO_DATE_TIME);
		
		return clock;
	}

	public Map<LocalDateTime, List<PositionAndClockRecord>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		List<PositionAndClockRecord> lstPositionAndClockRecord = new ArrayList<PositionAndClockRecord>();

		LocalDateTime clock = null;
		int nbSatelites = 32;
		try {
			clock = splitClockLine(readLine()); // On passe la clock
			for (int i = 0; i < nbSatelites; ++i) {
				lstPositionAndClockRecord.add(splitLine(readLine()));
			}
		} catch (NoSuchElementException e) {
			String message = "";
			throw new BusinessException(message, e);
		}

		Map<LocalDateTime, List<PositionAndClockRecord>> res = new HashMap<LocalDateTime, List<PositionAndClockRecord>>();
		res.put(clock, lstPositionAndClockRecord);
		
		return res;
	}
}
