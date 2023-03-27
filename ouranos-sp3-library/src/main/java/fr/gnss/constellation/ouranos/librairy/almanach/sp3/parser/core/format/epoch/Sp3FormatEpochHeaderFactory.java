package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public final class Sp3FormatEpochHeaderFactory {

  private Sp3FormatEpochHeaderFactory() {

  }

  public static ISp3FormatEpochHeader getParserEpochHeader(Sp3FileType sp3FileType) {

    return switch (sp3FileType) {
      case a -> new Sp3FormatEpochHeaderTypeA();
      case c -> new Sp3FormatEpochHeaderTypeC();
      default -> throw new UnsupportedOperationException(String.format("Parser Epoch Header with type %s, NotImplement", sp3FileType));
    };
  }

}
