package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;

public class Sp3Parser {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(Sp3Parser.class);

	private Sp3IHeaderParser sp3HeaderParser;
	private Sp3ICoreParser sp3CoreParser;

	public Sp3Parser(File sp3File) throws TechnicalException, BusinessException {
		this.loadParser(sp3File);
	}

	private void loadParser(File sp3File) throws TechnicalException,
			BusinessException {

		if (sp3File.exists() && sp3File.isFile()) {
			try {
				RandomAccessFile sp3HeaderReader = new RandomAccessFile(
						sp3File, "r");
				FileReader sp3CoreReader = new FileReader(sp3File);
				byte[] cbuf = new byte[2];

				sp3HeaderReader.read(cbuf);
				if (cbuf[0] == '#' && (cbuf[1] == 'c' || cbuf[1] == 'a')) {
					if (cbuf[1] == 'c') {
						sp3HeaderParser = new Sp3cHeaderParser(sp3HeaderReader);
						sp3CoreParser = new Sp3cCoreParser(sp3CoreReader);
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("Parser type c. [cbuf=" + cbuf + "]");
						}
					}
					if (cbuf[1] == 'a') {
						sp3HeaderParser = new Sp3aHeaderParser(sp3HeaderReader);
						sp3CoreParser = new Sp3aCoreParser(sp3CoreReader);
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("Parser type a. [cbuf=" + cbuf + "]");
						}
					}
				} else {
					String message = "La version n'est pas reconnu. [version="
							+ cbuf.toString() + "]";
					throw new BusinessException(message);
				}

			} catch (IOException e) {
				String message = "Impossible de lire les deux premiers caractères du fichier.";
				throw new TechnicalException(message);
			}
		} else {
			String message = "";
			throw new BusinessException(message);
		}
	}

	public String getVersionSymbol() throws TechnicalException {
		return sp3HeaderParser.getVersionSymbol();
	}

	public LocalDateTime getStartEpochRecord() throws TechnicalException {
		return sp3HeaderParser.getStartEpochRecord();
	}

	public int getNumberOfEpoch() throws TechnicalException {
		return sp3HeaderParser.getNumberOfEpoch();
	}

	public String getDataUsed() throws TechnicalException {
		return sp3HeaderParser.getDataUsed();
	}

	public String getCoordinateSystem() throws TechnicalException {
		return sp3HeaderParser.getCoordinateSystem();
	}

	public String getOrbitType() throws TechnicalException {
		return sp3HeaderParser.getOrbitType();
	}

	public String getAgency() throws TechnicalException {
		return sp3HeaderParser.getAgency();
	}

	public int getGPSWeek() throws TechnicalException {
		return sp3HeaderParser.getGPSWeek();
	}

	public double getSecondsOfWeek() throws TechnicalException {
		return sp3HeaderParser.getSecondsOfWeek();
	}

	public double getEpochInterval() throws TechnicalException {
		return sp3HeaderParser.getEpochInterval();
	}

	public int getModJulDaySt() throws TechnicalException {
		return sp3HeaderParser.getModJulDaySt();
	}

	public double getFractionalDay() throws TechnicalException {
		return sp3HeaderParser.getFractionalDay();
	}

	public int getNumber0fSats() throws TechnicalException {
		return sp3HeaderParser.getNumber0fSats();
	}

	public String[] getSatId() throws TechnicalException, BusinessException {
		return sp3HeaderParser.getSatId();
	}

	public int[] getSatAccuracy() throws TechnicalException, BusinessException {
		return sp3HeaderParser.getSatAccuracy();
	}

	public String getFileType() throws TechnicalException {
		return sp3HeaderParser.getFileType();
	}

	public String getTimeSystem() throws TechnicalException {
		return sp3HeaderParser.getTimeSystem();
	}

	public Map<LocalDateTime, List<PositionAndClockRecord>> getPositionAndClockRecord()
			throws TechnicalException, BusinessException {
		return sp3CoreParser.getPositionAndClockRecord();
	}
}
