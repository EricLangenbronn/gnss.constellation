package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.format.firstline;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public interface ISp3FormatFirstLine {

    void parseFirstLine(String line, Sp3Header sp3Header);

}
