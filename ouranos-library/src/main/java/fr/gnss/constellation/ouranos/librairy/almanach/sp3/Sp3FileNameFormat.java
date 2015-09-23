package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;

public class Sp3FileNameFormat {

	private EphemerideType ephemerideType; // igs, igr, igu
	private int gpsWeek; // GPS Week, week 0000 is Jan. 6-12, 1980
	private int day; // Day of week (Sun=0 --> Sat=6, Full Week=7)
	private int hour; // Start hour (00,06,12,18)
	private OrbitType orbitType; // Orbit, other extensions are clk,cls,erp,sum
	private String compressType; // Z Files are Unix compressed

	/**
	 * Initializes a newly created Sp3FileNameFormat object.
	 * 
	 * @param p_fileName
	 *            - A file name string.
	 * @throws BusinessException
	 */
	public Sp3FileNameFormat(String p_fileName) throws BusinessException {
		this.valueOf(p_fileName);
	}

	/**
	 * Returns the Sp3FileNameFormat representation of the string argument.
	 * 
	 * @param p_fileName
	 *            - a string.
	 * @throws BusinessException
	 */
	private void valueOf(String p_fileName) throws BusinessException {
		if (StringUtils.isBlank(p_fileName)) {
			String message = "Le nom du fichier doit être renseigné. [fileName=" + p_fileName + "]";
			throw new BusinessException(message);
		}

		try {
			String[] l_fileName = p_fileName.split("\\.");

			ephemerideType = EphemerideType.stringToEphemerideType(l_fileName[0].substring(0, 3));
			gpsWeek = Integer.parseInt(l_fileName[0].substring(3, 7));
			day = Integer.parseInt(l_fileName[0].substring(7, 8));

			if (l_fileName[0].length() > 8) {
				hour = Integer.parseInt(l_fileName[0].substring(9, 11));
			} else {
				hour = -1;
			}

			orbitType = OrbitType.stringToOrbitTypeType(l_fileName[1]);

			if (l_fileName.length > 2) {
				compressType = l_fileName[2];
			}
		} catch (NumberFormatException e) {
			String l_message = "Impossible de parser le nom du fichier. [fileName=" + p_fileName + "]";
			throw new BusinessException(l_message, e);
		}
	}

	/**
	 * Get the type of the ephemeride.
	 * 
	 * @return the type of the ephemeride.
	 */
	public EphemerideType getEphemerideType() {
		return ephemerideType;
	}

	/**
	 * Get the number of week from 1980-01-06
	 * 
	 * @return the number of GPS week.
	 */
	public int getGpsWeek() {
		return gpsWeek;
	}

	/**
	 * Get the day of the week.
	 * 
	 * @return the day of the week.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Get the start hour, if EphemeridType is "igu", -1 otherwise.
	 * 
	 * @return the start hour.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Get the orbit type.
	 * 
	 * @return the orbit type.
	 */
	public OrbitType getOrbitType() {
		return orbitType;
	}

	/**
	 * Get the type of the compression.
	 * 
	 * @return the type of the compression.
	 */
	public String getCompressType() {
		return compressType;
	}

	/**
	 * Return true if compress, otherwise false.
	 * 
	 * @return true if compress, otherwise false.
	 */
	public boolean isCompress() {
		if (StringUtils.isBlank(compressType)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Sp3FileNameFormat [ephemerideType=" + ephemerideType + ", gpsWeek=" + gpsWeek + ", day=" + day
				+ ", hour=" + hour + ", orbitType=" + orbitType + ", compressType=" + compressType + "]";
	}
}