package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatEpochHeaderFactory {

	private Sp3FormatEpochHeaderFactory() {

	}

	public static AbstractSp3FormatEpochHeader getParserEpochHeader(Sp3FileType sp3FileType) {

		switch (sp3FileType) {
		case c:
			return new Sp3FormatEpochHeaderTypeC();
		default:
			throw new UnsupportedOperationException("Parser Epoch Header with type " + sp3FileType + ", NotImplement");
		}
	}

}
