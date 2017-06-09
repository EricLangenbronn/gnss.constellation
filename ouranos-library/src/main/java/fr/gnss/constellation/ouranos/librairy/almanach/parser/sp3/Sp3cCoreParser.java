package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.AbstractCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.format.Sp3CFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;

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

	private SatellitePosition<CartesianCoordinate3D> splitSateliteLine(String line) throws BusinessException {
		Objects.requireNonNull(line);

		return Sp3CFormat.formatSatelitePosition(line);
	}

	public LocalDateTime splitClockLine(String line) throws BusinessException {
		Objects.requireNonNull(line);

		return Sp3CFormat.formatEpochHeader(line);
	}

	@Override
	public SatelliteTimeCoordinate<CartesianCoordinate3D> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		SatelliteTimeCoordinate<CartesianCoordinate3D> sateliteByTime = null;

		LocalDateTime epochHeaderRecord = null;
		try {
			epochHeaderRecord = splitClockLine(readLine()); // On passe la clock
			sateliteByTime = new SatelliteTimeCoordinate<CartesianCoordinate3D>(epochHeaderRecord);
			for (int i = 0; i < numberOfSat; ++i) {
				SatellitePosition<CartesianCoordinate3D> satellite = splitSateliteLine(readLine());
				sateliteByTime.addSatellite(satellite.getVehicleId(), satellite);
			}
		} catch (NoSuchElementException e) {
			String message = "Fin du fichier, impossible de lire plus";
			throw new BusinessException(message, e);
		}

		return sateliteByTime;
	}

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getPeriodOfPosition(final LocalDateTime start,
			final LocalDateTime end) throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> satelitesByTime = new ArrayList<>();

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
		while (wrapStart.compareTo(end) <= 0) {
			satelitesByTime.add(getPositionAndClockRecord());

			wrapStart = wrapStart.plusMinutes(NB_MIN_MEASURE);
		}

		return satelitesByTime;
	}
}
