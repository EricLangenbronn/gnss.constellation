package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch.ISp3FormatEpochHeader;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch.Sp3FormatEpochHeaderFactory;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position.ISp3FormatPositionAndClock;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position.Sp3FormatPositionAndClockFactory;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import java.time.LocalDateTime;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class Sp3CoreParser {

  private static final String CLOCK_LINE = "*";
  private static final String POSITION_LINE = "P";

  private final ISp3FormatEpochHeader sp3ParserEpochHeader;
  private final ISp3FormatPositionAndClock<CartesianCoordinate3D> sp3ParserPositionAndClock;

  public Sp3CoreParser(final Sp3FileType sp3FileType) {
    Objects.requireNonNull(sp3FileType, "sp3 type");

    this.sp3ParserEpochHeader = Sp3FormatEpochHeaderFactory.getParserEpochHeader(sp3FileType);
    this.sp3ParserPositionAndClock = Sp3FormatPositionAndClockFactory.getParserPositionAndClock(sp3FileType);
  }

  public static boolean isClockLine(String line) {
    if (StringUtils.isBlank(line)) {
      return false;
    } else {
      return CLOCK_LINE.equals(line.substring(0, 1));
    }
  }

  public static boolean isPositionLine(String line) {
    if (StringUtils.isBlank(line)) {
      return false;
    } else {
      return POSITION_LINE.equals(line.substring(0, 1));
    }
  }

  public LocalDateTime parseEpochHeader(String line) {
    return sp3ParserEpochHeader.parseEpochHeader(line);
  }

  public SatellitePosition<CartesianCoordinate3D> parsePositionAndClock(String line) {
    return sp3ParserPositionAndClock.parsePositionAndClock(line);
  }

  public TimeCoordinateSatellitePosition<CartesianCoordinate3D> parsePositionsAndClock(String clockLine, String[] positionLines) {

    LocalDateTime epochHeaderRecord = sp3ParserEpochHeader.parseEpochHeader(clockLine);
    TimeCoordinateSatellitePosition<CartesianCoordinate3D> sateliteByTime = new TimeCoordinateSatellitePosition<>(epochHeaderRecord);

    for (String positionLine : positionLines) {
      SatellitePosition<CartesianCoordinate3D> satelitePosition = sp3ParserPositionAndClock.parsePositionAndClock(positionLine);
      sateliteByTime.addSatellite(satelitePosition.getVehicleId(), satelitePosition);
    }

    return sateliteByTime;
  }

  public TimeCoordinateSatellitePosition<CartesianCoordinate3D> parsePositionsAndClock(LocalDateTime clock, String[] positionLines) {

    TimeCoordinateSatellitePosition<CartesianCoordinate3D> sateliteByTime = new TimeCoordinateSatellitePosition<>(clock);

    for (String positionLine : positionLines) {
      SatellitePosition<CartesianCoordinate3D> satelitePosition = sp3ParserPositionAndClock.parsePositionAndClock(positionLine);
      sateliteByTime.addSatellite(satelitePosition.getVehicleId(), satelitePosition);
    }

    return sateliteByTime;
  }

}
