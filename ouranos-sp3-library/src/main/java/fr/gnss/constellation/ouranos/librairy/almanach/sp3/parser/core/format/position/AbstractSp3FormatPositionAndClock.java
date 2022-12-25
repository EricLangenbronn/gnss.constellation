package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public abstract class AbstractSp3FormatPositionAndClock<T extends ICoordinate> {
	
	public abstract SatellitePosition<T> parsePositionAndClock(String line);
}
