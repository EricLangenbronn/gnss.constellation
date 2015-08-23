package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.librairy.almanach.sp3.Satelite;

public class Sp3cCoreParser extends AbstractCoreParser implements
		Sp3ICoreParser {

	private static final long END_HEADER = 1342;
	
	private LocalDateTime startMeasure;
	private int numberOfSat;

	public Sp3cCoreParser(FileReader p_fr, LocalDateTime p_startMeasure, int p_nbSat) throws TechnicalException {
		super(p_fr);
		startMeasure = p_startMeasure;
		numberOfSat = p_nbSat;
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

	private Satelite splitLine(String line)
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

		Satelite res = new Satelite(vehicleId, x,
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

	public Entry<LocalDateTime, List<Satelite>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		List<Satelite> lstPositionAndClockRecord = new ArrayList<>();

		LocalDateTime clock = null;
		try {
			clock = splitClockLine(readLine()); // On passe la clock
			for (int i = 0; i < numberOfSat; ++i) {
				lstPositionAndClockRecord.add(splitLine(readLine()));
			}
		} catch (NoSuchElementException e) {
			String message = "";
			throw new BusinessException(message, e);
		}

	
		
		return new SimpleEntry<LocalDateTime, List<Satelite>>(clock, lstPositionAndClockRecord);
	}

	@Override
	public List<Entry<LocalDateTime, List<Satelite>>> getPeriodOfPosition(final LocalDateTime start,
			final LocalDateTime end) throws TechnicalException, BusinessException {
		
		List<Entry<LocalDateTime, List<Satelite>>> res = new ArrayList<>();
		
		LocalDateTime wrapStartMeasure = startMeasure.plusSeconds(0);
		while(wrapStartMeasure.compareTo(start) < 0) {
			for(int i = 0; i < numberOfSat; ++i) {
				readLine();//position sat
			}
			readLine();//position de la clock
			wrapStartMeasure = wrapStartMeasure.plusMinutes(NB_MIN_MEASURE);
		}
		//debut acquisition des données
		LocalDateTime wrapStart = start.plusSeconds(0); // tips to copy date
		while(wrapStart.compareTo(end) < 0) {
			res.add(getPositionAndClockRecord());
			
			wrapStart = wrapStart.plusMinutes(NB_MIN_MEASURE);
		}
		
		return res;
	}
}
