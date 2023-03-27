package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public sealed interface ISp3FormatPositionAndClock<T extends ICoordinate>
    permits Sp3FormatPositionAndClockTypeA, Sp3FormatPositionAndClockTypeC {

  SatellitePosition<T> parsePositionAndClock(String line);
}
