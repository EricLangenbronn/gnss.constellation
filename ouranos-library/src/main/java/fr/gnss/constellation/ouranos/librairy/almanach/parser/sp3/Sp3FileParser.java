package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.Closeable;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.core.AbstractCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.header.AbstractHeaderParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.a.Sp3aCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.a.Sp3aHeaderParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.c.Sp3cCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.c.Sp3cHeaderParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3FileParser implements ISp3FileParser, Closeable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3FileParser.class);

	// -------------------- Propriétés de la classe --------------------

	private AbstractHeaderParser sp3HeaderParser;
	private AbstractCoreParser sp3CoreParser;

	// ------------------------ Constructeur(s) ------------------------

	public Sp3FileParser(Sp3File sp3File) throws TechnicalException, BusinessException {
		this.loadParser(sp3File);
	}

	// ----------------------- Methodes internes -----------------------

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
				this.sp3CoreParser = new Sp3cCoreParser(fileReaderCore, sp3HeaderParser.getStartEpochRecord(), sp3HeaderParser.getNumber0fSats());
				break;
			case d:
				throw new TechnicalException("NotImplement");
			default:
				throw new TechnicalException("unknow value for sp3 file type");
			}

		} catch (Exception e) {
			String message = "Impossible de créer le parser.";
			throw new TechnicalException(message);
		}

	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String getVersionSymbol() throws TechnicalException {
		return sp3HeaderParser.getVersionSymbol();
	}

	@Override
	public LocalDateTime getStartEpochRecord() throws TechnicalException {
		return sp3HeaderParser.getStartEpochRecord();
	}

	@Override
	public int getNumberOfEpoch() throws TechnicalException {
		return sp3HeaderParser.getNumberOfEpoch();
	}

	@Override
	public String getDataUsed() throws TechnicalException {
		return sp3HeaderParser.getDataUsed();
	}

	@Override
	public String getCoordinateSystem() throws TechnicalException {
		return sp3HeaderParser.getCoordinateSystem();
	}

	@Override
	public String getOrbitType() throws TechnicalException {
		return sp3HeaderParser.getOrbitType();
	}

	@Override
	public String getAgency() throws TechnicalException {
		return sp3HeaderParser.getAgency();
	}

	@Override
	public int getGPSWeek() throws TechnicalException {
		return sp3HeaderParser.getGPSWeek();
	}

	@Override
	public double getSecondsOfWeek() throws TechnicalException {
		return sp3HeaderParser.getSecondsOfWeek();
	}

	@Override
	public double getEpochInterval() throws TechnicalException {
		return sp3HeaderParser.getEpochInterval();
	}

	@Override
	public int getModJulDaySt() throws TechnicalException {
		return sp3HeaderParser.getModJulDaySt();
	}

	@Override
	public double getFractionalDay() throws TechnicalException {
		return sp3HeaderParser.getFractionalDay();
	}

	@Override
	public int getNumber0fSats() throws TechnicalException {
		return sp3HeaderParser.getNumber0fSats();
	}

	@Override
	public String[] getSatId() throws TechnicalException, BusinessException {
		return sp3HeaderParser.getSatId();
	}

	@Override
	public int[] getSatAccuracy() throws TechnicalException, BusinessException {
		return sp3HeaderParser.getSatAccuracy();
	}

	@Override
	public String getFileType() throws TechnicalException {
		return sp3HeaderParser.getFileType();
	}

	@Override
	public String getTimeSystem() throws TechnicalException {
		return sp3HeaderParser.getTimeSystem();
	}

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getPositionAndClockRecord(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {

		return sp3CoreParser.getPeriodOfPosition(start, end);
	}

	@Override
	public void close() {
		this.sp3HeaderParser.close();
		this.sp3CoreParser.close();

	}

}
