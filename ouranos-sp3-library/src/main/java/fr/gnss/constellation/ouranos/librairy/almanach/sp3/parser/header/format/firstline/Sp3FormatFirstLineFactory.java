package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatFirstLineFactory {

  private Sp3FormatFirstLineFactory() {

  }

  public static ISp3FormatFirstLine getParserFirstLine(Sp3FileType sp3FileType) {

    return switch (sp3FileType) {
      case a -> new Sp3FormatFirstLineTypeA();
      case c -> new Sp3FormatFirstLineTypeC();
      default -> throw new IllegalArgumentException(String.format("Parser First Line with type %s, NotImplement", sp3FileType));
    };
  }

}
