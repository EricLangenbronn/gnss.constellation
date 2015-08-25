package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

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

	public Sp3FileNameFormat(String p_fileName) throws BusinessException {
		this.parseFileName(p_fileName);
	}

	private void parseFileName(String p_fileName) throws BusinessException {
		if (p_fileName == null || p_fileName.isEmpty()) {
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

	public EphemerideType getEphemerideType() {
		return ephemerideType;
	}

	public int getGpsWeek() {
		return gpsWeek;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public OrbitType getOrbitType() {
		return orbitType;
	}

	public String getCompressType() {
		return compressType;
	}

	public boolean isCompress() {
		if (compressType == null || compressType.isEmpty()) {
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