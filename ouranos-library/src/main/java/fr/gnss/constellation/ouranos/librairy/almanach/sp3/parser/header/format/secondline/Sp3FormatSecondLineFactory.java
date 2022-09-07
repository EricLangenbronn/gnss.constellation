package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatSecondLineFactory {

    private Sp3FormatSecondLineFactory() {

    }

    public static AbstractSp3FormatSecondLine getParserSecondLine(Sp3FileType sp3FileType) {

        return switch (sp3FileType) {
            case a -> new Sp3FormatSecondLineTypeA();
            case c -> new Sp3FormatSecondLineTypeC();
            default -> throw new IllegalArgumentException("Parser Second Line with type " + sp3FileType + ", NotImplement");
        };
    }

}
