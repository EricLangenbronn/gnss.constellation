package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3FormatFirstLineFactory {

	private Sp3FormatFirstLineFactory() {

	}

	public static AbstractSp3FormatFirstLine getParserFirstLine(Sp3FileType sp3FileType) {

		switch (sp3FileType) {
		case c:
			return new Sp3FormatFirstLineTypeC();
		default:
			throw new RuntimeException("Parser First Line with type " + sp3FileType + ", NotImplement");
		}
	}

}
