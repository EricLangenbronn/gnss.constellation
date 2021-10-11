package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatSatelliteIdFactory {

	private Sp3FormatSatelliteIdFactory() {

	}

	public static AbstractSp3FormatSatelliteId getParserSatelliteId(Sp3FileType sp3FileType) {

		switch (sp3FileType) {
		case c:
			return new Sp3FormatSatelliteIdTypeC();
		default:
			throw new RuntimeException("Parser Satellite Id with type " + sp3FileType + ", NotImplement");
		}
	}

}
