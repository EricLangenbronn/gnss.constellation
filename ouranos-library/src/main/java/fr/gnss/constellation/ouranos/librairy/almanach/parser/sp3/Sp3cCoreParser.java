package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;

public class Sp3cCoreParser extends AbstractCoreParser implements ISp3CoreParser {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3cCoreParser.class);

	private static final long FIRST_CHARACTER_CORE_POSITION = 1342;

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
			sp3Buffer.skip(FIRST_CHARACTER_CORE_POSITION);
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}
	}

	private Sp3SateliteInformation splitSateliteLine(String line) throws BusinessException {
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

		// the position values are in km and have to be converted to m
		Sp3SateliteInformation res = new Sp3SateliteInformation(vehicleId, x * 1000, y * 1000, z * 1000);

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

		String dateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + sec;

		LocalDateTime clock = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);

		return clock;
	}

	@Override
	public SateliteTimeCoordinate getPositionAndClockRecord() throws TechnicalException, BusinessException {
		SateliteTimeCoordinate sateliteByTime = null;

		LocalDateTime epochHeaderRecord = null;
		try {
			epochHeaderRecord = splitClockLine(readLine()); // On passe la clock
			sateliteByTime = new SateliteTimeCoordinate(epochHeaderRecord);
			for (int i = 0; i < numberOfSat; ++i) {
				sateliteByTime.addSatellite(splitSateliteLine(readLine()));
			}
		} catch (NoSuchElementException e) {
			String message = "Fin du fichier, impossible de lire plus";
			throw new BusinessException(message, e);
		}

		return sateliteByTime;
	}

	@Override
	public List<SateliteTimeCoordinate> getPeriodOfPosition(final LocalDateTime start,
			final LocalDateTime end) throws TechnicalException, BusinessException {

		List<SateliteTimeCoordinate> satelitesByTime = new ArrayList<>();

		// on se de place jusqu'a la bonne epoch
		LocalDateTime wrapStartMeasure = startMeasure.plusSeconds(0);
		while (wrapStartMeasure.compareTo(start) < 0) {
			for (int i = 0; i < numberOfSat; ++i) {
				readLine();// position sat
			}
			readLine();// position de la clock
			wrapStartMeasure = wrapStartMeasure.plusMinutes(NB_MIN_MEASURE);
		}

		// debut acquisition des données
		LocalDateTime wrapStart = start.plusSeconds(0); // tips to copy date
		while (wrapStart.compareTo(end) < 0) {
			satelitesByTime.add(getPositionAndClockRecord());

			wrapStart = wrapStart.plusMinutes(NB_MIN_MEASURE);
		}

		return satelitesByTime;
	}
}
