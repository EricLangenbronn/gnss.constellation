package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public abstract class AbstractSp3FormatSecondLine {

	public abstract void parseSecondLine(String line, Sp3Header sp3Header);

}
