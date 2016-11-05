package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.FileReader;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;

public class Sp3FileParser implements ISp3FileParser {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3FileParser.class);

	private ISp3HeaderParser sp3HeaderParser;
	private ISp3CoreParser sp3CoreParser;

	public Sp3FileParser(Sp3File sp3File) throws TechnicalException, BusinessException {
		this.loadParser(sp3File);
	}

	private void loadParser(Sp3File sp3File) throws TechnicalException, BusinessException {
		Sp3FileType sp3FileType = sp3File.getSp3FileType();

		try {
			RandomAccessFile accessFileHeader = new RandomAccessFile(sp3File.getFile(), "r");
			FileReader fileReaderCore = new FileReader(sp3File.getFile());
			switch (sp3FileType) {
			case a:
				this.sp3HeaderParser = new Sp3aHeaderParser(accessFileHeader);
				this.sp3CoreParser = new Sp3aCoreParser(fileReaderCore);
			case b:
				throw new TechnicalException("NotImplement");
			case c:
				this.sp3HeaderParser = new Sp3cHeaderParser(accessFileHeader);
				this.sp3CoreParser = new Sp3cCoreParser(fileReaderCore, sp3HeaderParser.getStartEpochRecord(),
						sp3HeaderParser.getNumber0fSats());
				break;
			case d:
				throw new TechnicalException("NotImplement");
			}
		} catch (Exception e) {
			String message = "Impossible de créer le parser";
			throw new TechnicalException(message, e);
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

	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getPositionAndClockRecordAll()
			throws TechnicalException, BusinessException {
		return null; // sp3CoreParser.getPositionAndClockRecord();
	}

	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getPositionAndClockRecord(LocalDateTime start,
			LocalDateTime end) throws TechnicalException, BusinessException {
		return sp3CoreParser.getPeriodOfPosition(start, end);
	}

	@Override
	public void close() {
		this.sp3HeaderParser.close();
		this.sp3CoreParser.close();

	}
}
