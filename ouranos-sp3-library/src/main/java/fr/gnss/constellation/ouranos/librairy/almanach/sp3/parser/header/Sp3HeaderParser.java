package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline.AbstractSp3FormatFirstLine;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline.Sp3FormatFirstLineFactory;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline.AbstractSp3FormatSecondLine;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline.Sp3FormatSecondLineFactory;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid.AbstractSp3FormatSatelliteId;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid.Sp3FormatSatelliteIdFactory;

public class Sp3HeaderParser {

	private static final String FIRST_LINE = "#";
	private static final String SECOND_LINE = "##";
	private static final String SATELLITE_ID = "+ ";
	private static final String ORBIT_ACCURACY = "++";

	private final AbstractSp3FormatFirstLine sp3FormatFirstLine;
	private final AbstractSp3FormatSecondLine sp3FormatSecondLine;
	private final AbstractSp3FormatSatelliteId sp3FormatSatelliteId;

	private final Sp3FileType sp3FileType;

	// check type P or V position and postition and velocity

	public Sp3HeaderParser(final Sp3FileType sp3FileType) {
		Objects.requireNonNull(sp3FileType, "sp3 type");

		this.sp3FormatFirstLine = Sp3FormatFirstLineFactory.getParserFirstLine(sp3FileType);
		this.sp3FormatSecondLine = Sp3FormatSecondLineFactory.getParserSecondLine(sp3FileType);
		this.sp3FormatSatelliteId = Sp3FormatSatelliteIdFactory.getParserSatelliteId(sp3FileType);

		this.sp3FileType = sp3FileType;
	}

	public Sp3Header loadSp3Header(String[] header) throws IOException {
		Sp3Header sp3Header = new Sp3Header();

		for (String s : header) {
			if (isFirstLine(sp3FileType, s)) {
				sp3FormatFirstLine.parseFirstLine(s, sp3Header);
			}

			if (isSecondLine(s)) {
				sp3FormatSecondLine.parseSecondLine(s, sp3Header);
			}

			if (isSatelliteIdLine(s)) {
				sp3FormatSatelliteId.parseSatelliteId(s, sp3Header);
			}
		}

		return sp3Header;
	}

	public void loadFirstLine(String line, Sp3Header sp3Header) {
		sp3FormatFirstLine.parseFirstLine(line, sp3Header);
	}

	public void loadSecondLine(String line, Sp3Header sp3Header) {
		sp3FormatSecondLine.parseSecondLine(line, sp3Header);
	}

	public void loadSatId(String line, Sp3Header sp3Header) {
		sp3FormatSatelliteId.parseSatelliteId(line, sp3Header);
	}

	public static boolean isFirstLine(Sp3FileType sp3FileType, String line) {
		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
			return false;
		} else {
			return (FIRST_LINE + sp3FileType).equals(line.substring(0, 2));
		}
	}

	public static boolean isSecondLine(String line) {
		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
			return false;
		} else {
			return SECOND_LINE.equals(line.substring(0, 2));
		}
	}

	public static boolean isSatelliteIdLine(String line) {
		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
			return false;
		} else {
			return SATELLITE_ID.equals(line.substring(0, 2));
		}
	}

	public static boolean isOrbitAccuracyLine(String line) {
		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
			return false;
		} else {
			return ORBIT_ACCURACY.equals(line.substring(0, 2));
		}
	}
}
