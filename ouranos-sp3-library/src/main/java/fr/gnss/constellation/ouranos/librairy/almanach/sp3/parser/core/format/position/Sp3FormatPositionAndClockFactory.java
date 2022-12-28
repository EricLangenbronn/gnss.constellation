package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3FormatPositionAndClockFactory {

    private Sp3FormatPositionAndClockFactory() {

    }

    public static ISp3FormatPositionAndClock<CartesianCoordinate3D> getParserPositionAndClock(Sp3FileType sp3FileType) {

        return switch (sp3FileType) {
            case a -> new Sp3FormatPositionAndClockTypeA();
            case c -> new Sp3FormatPositionAndClockTypeC();
            default ->
                    throw new RuntimeException(String.format("Parser Position And Clock with type %s, NotImplement", sp3FileType));
        };
    }

}
