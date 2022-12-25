package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.fromat.satelliteid;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;

public abstract class AbstractSp3FormatSatelliteId {

	public abstract void parseSatelliteId(String line, Sp3Header sp3Header);

}
