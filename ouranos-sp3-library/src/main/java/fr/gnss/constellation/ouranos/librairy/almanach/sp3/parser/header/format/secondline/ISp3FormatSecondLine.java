package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.secondline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public interface ISp3FormatSecondLine {

  void parseSecondLine(String line, Sp3Header sp3Header);

}
