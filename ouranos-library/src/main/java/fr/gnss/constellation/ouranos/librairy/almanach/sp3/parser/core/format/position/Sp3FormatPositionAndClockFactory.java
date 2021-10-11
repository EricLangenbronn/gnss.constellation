package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3FormatPositionAndClockFactory {

	private Sp3FormatPositionAndClockFactory() {

	}

	public static AbstractSp3FormatPositionAndClock<CartesianCoordinate3D> getParserPositionAndClock(Sp3FileType sp3FileType) {

		switch (sp3FileType) {
		case c:
			return new Sp3FormatPositionAndClockTypeC();
		default:
			throw new RuntimeException("Parser Position And Clock with type " + sp3FileType + ", NotImplement");
		}
	}

}
