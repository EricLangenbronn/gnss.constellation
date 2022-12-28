package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatSatelliteIdFactory {

    private Sp3FormatSatelliteIdFactory() {

    }

    public static ISp3FormatSatelliteId getParserSatelliteId(Sp3FileType sp3FileType) {

        return switch (sp3FileType) {
            case a -> new Sp3FormatSatelliteIdTypeA();
            case c -> new Sp3FormatSatelliteIdTypeC();
            default ->
                    throw new IllegalArgumentException(String.format("Parser Satellite Id with type %s, NotImplement", sp3FileType));
        };
    }

}
