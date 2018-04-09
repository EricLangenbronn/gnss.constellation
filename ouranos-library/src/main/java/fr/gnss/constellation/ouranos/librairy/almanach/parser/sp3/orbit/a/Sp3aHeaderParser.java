package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.a;

import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.header.AbstractHeaderParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.header.ISp3HeaderParser;

public class Sp3aHeaderParser extends AbstractHeaderParser implements ISp3HeaderParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3aHeaderParser.class);
	
	// -------------------- Propriétés de la classe --------------------

	private static int headerNbCol = 60;
	
	// ------------------------ Constructeur(s) ------------------------

	public Sp3aHeaderParser(RandomAccessFile p_fr) {
		super(p_fr);
	}
	
	// ----------------------- Methodes internes -----------------------

	public String getVersionSymbol() throws TechnicalException {
		return super.read(0, 2);
	}

	public LocalDateTime getStartEpochRecord() throws TechnicalException {
		// 2014-09-02T08:05:23.653Z

		String year = super.read(3, 4);
		String month = super.read(8, 2).replaceFirst(" ", "0");
		String day = super.read(11, 2).replaceFirst(" ", "0");
		String hour = super.read(14, 2).replaceFirst(" ", "0");
		String minute = super.read(17, 2).replaceFirst(" ", "0");
		String sec = super.read(20, 11).replaceFirst(" ", "0");

		String dateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + sec + "Z";
		LocalDateTime startDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_INSTANT);

		return startDateTime;
	}

	public int getNumberOfEpoch() throws TechnicalException {

		int res = -1;
		try {
			res = Integer.parseInt(super.read(32, 7).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public String getDataUsed() throws TechnicalException {
		return super.read(40, 5).trim();
	}

	public String getCoordinateSystem() throws TechnicalException {
		return super.read(46, 5);
	}

	public String getOrbitType() throws TechnicalException {
		return super.read(52, 3);
	}

	public String getAgency() throws TechnicalException {
		return super.read(56, 4).trim();

	}

	// ligne 2 du ficher sp2 ((nbLigne-1) * (nbCol (60 dans notre cas)) ) +
	// (nbLigne-1) + colLigneCourante
	// le tout -1 car en java on commence à zero
	public int getGPSWeek() throws TechnicalException {

		int res = -1;
		try {
			res = Integer.parseInt(super.read(64, 4).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public double getSecondsOfWeek() throws TechnicalException {

		double res = -1;
		try {
			res = Double.parseDouble(super.read(68, 15).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public double getEpochInterval() throws TechnicalException {

		double res = -1;
		try {
			res = Double.parseDouble(super.read(84, 14).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public int getModJulDaySt() throws TechnicalException {

		int res = -1;
		try {
			res = Integer.parseInt(super.read(100, 5).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public double getFractionalDay() throws TechnicalException {

		double res = -1;
		try {
			res = Double.parseDouble(super.read(105, 15).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public int getNumber0fSats() throws TechnicalException {

		int res = -1;
		try {
			res = Integer.parseInt(super.read(126, 2).trim());
		} catch (NumberFormatException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return res;
	}

	public String[] getSatId() throws TechnicalException, BusinessException {

		// Le premier statelite se trouve sur la ligne 3
		// L'offset de début de debut de la ligne 3 est = 122
		// 9 caractère avant les satelites id, 17 satelite max par ligne
		// Le'equiation : 122 + 9 +(((i/17) * 9) + (i/17)) + (i*3)

		String[] statelitesId;
		try {
			statelitesId = new String[this.getNumber0fSats()];
			for (int i = 0; i < this.getNumber0fSats(); ++i) {
				int offset = 122 + 9 + (((i / 17) * 9) + (i / 17)) + (i * 3);
				statelitesId[i] = super.read(offset, 3);
			}
		} catch (IndexOutOfBoundsException e) {
			String message = "";
			throw new BusinessException(message, e);
		}

		return statelitesId;
	}

	public int[] getSatAccuracy() throws TechnicalException, BusinessException {

		// Le premier statelite se trouve sur la ligne 3
		// L'offset de début de debut de la ligne 3 est = 427
		// 12 caractère avant les satelites id, 17 satelite max par ligne
		// Le'equiation : 427 + 9 +(((i/17) * 9) + (i/17)) + (i*3)

		int[] staAccuray;
		try {
			staAccuray = new int[this.getNumber0fSats()];
			for (int i = 0; i < this.getNumber0fSats(); ++i) {
				int offset = 427 + 9 + (((i / 17) * 9) + (i / 17)) + (i * 3);
				staAccuray[i] = Integer.parseInt(super.read(offset, 3).trim());
			}
		} catch (IndexOutOfBoundsException e) {
			String message = "";
			throw new BusinessException(message, e);

		} catch (NumberFormatException e) {
			String message = "";
			throw new BusinessException(message, e);
		}

		return staAccuray;
	}

	public String getFileType() throws TechnicalException {
		return super.read(735, 2).trim();
	}

	public String getTimeSystem() throws TechnicalException {
		return super.read(741, 3);
	}
}
