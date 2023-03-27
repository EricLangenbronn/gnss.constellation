package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestSp3Iterator {

  @Test
  public void testWhenFileExist_thenObjectOk() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
    assertNotNull(sp3Reader);

    Sp3Iterator sp3Iterator = new Sp3Iterator(sp3Reader);
    assertNotNull(sp3Iterator);
  }

  @Test
  public void testWhenFileExist_thenHasNext() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
    assertNotNull(sp3Reader);

    Sp3Iterator sp3Iterator = new Sp3Iterator(sp3Reader);
    assertNotNull(sp3Iterator);
    assertTrue(sp3Iterator.hasNext());

  }

  @Test
  public void testWhenReadFirstPeriod_thenHasFirstPeriod() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
    assertNotNull(sp3Reader);

    Sp3Iterator sp3Iterator = new Sp3Iterator(sp3Reader);
    assertNotNull(sp3Iterator);

    TimeCoordinateSatellitePosition<CartesianCoordinate3D> positionsAndClockRecord = sp3Iterator.next();
    assertNotNull(positionsAndClockRecord);
    assertEquals(LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME), positionsAndClockRecord.getEpochHeaderRecord());
    assertEquals(32, positionsAndClockRecord.getSatellites().size());
    SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClockRecord.getSatellites().get("G01");
    assertArrayEquals(new double[] {-1.7978872222000003E7, -5143579.1280000005, 1.8870417705E7}, satelliteG01.getPosition().getPosition());

  }

  @Test
  public void testWhenReadSecondPeriod_thenHasSecondPeriod() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
    assertNotNull(sp3Reader);

    Sp3Iterator sp3Iterator = new Sp3Iterator(sp3Reader);
    assertNotNull(sp3Iterator);

    TimeCoordinateSatellitePosition<CartesianCoordinate3D> positionsAndClockRecord = sp3Iterator.next();
    positionsAndClockRecord = sp3Iterator.next();

    assertNotNull(positionsAndClockRecord);
    assertEquals(LocalDateTime.parse("2013-12-22T00:15", DateTimeFormatter.ISO_DATE_TIME), positionsAndClockRecord.getEpochHeaderRecord());
    assertEquals(32, positionsAndClockRecord.getSatellites().size());
    SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClockRecord.getSatellites().get("G01");
    assertArrayEquals(new double[] {-1.8928577316E7, -6990226.154, 1.7294914165E7}, satelliteG01.getPosition().getPosition());

  }

  @Test
  public void testWhenReadAllFile_thenHasAllFile() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
    assertNotNull(sp3Reader);

    Sp3Iterator sp3Iterator = new Sp3Iterator(sp3Reader);
    assertNotNull(sp3Iterator);

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> positionsAndClocksRecords = new ArrayList<>();
    while (sp3Iterator.hasNext()) {
      TimeCoordinateSatellitePosition<CartesianCoordinate3D> positionsAndClockRecord = sp3Iterator.next();
      positionsAndClocksRecords.add(positionsAndClockRecord);
    }

    assertNotNull(positionsAndClocksRecords);
    assertFalse(positionsAndClocksRecords.isEmpty());
    assertEquals(96, positionsAndClocksRecords.size());
    for (TimeCoordinateSatellitePosition<CartesianCoordinate3D> positionsAndClockRecord : positionsAndClocksRecords) {
      assertEquals(32, positionsAndClockRecord.getSatellites().size());
    }

  }

}
