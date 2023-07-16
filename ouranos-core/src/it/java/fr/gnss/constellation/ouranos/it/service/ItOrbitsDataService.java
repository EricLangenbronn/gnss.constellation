package fr.gnss.constellation.ouranos.it.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.orbitdata.service.sequential.OrbitDataSequentialService;
import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class ItOrbitsDataService {

  @Inject
  private OrbitDataSequentialService orbitsDataService;

  @Test
  public void testReadDatasForPeriodStartAfterEndBefore() {

    LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);


    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> datas = orbitsDataService.getCartesianPositionsForPeriod(start, end,
        EphemerideType.igs, OrbitType.sp3);

    assertNotNull(datas);
    assertEquals(20, datas.size());
  }

  @Test
  public void testReadDatasForPeriodStartEqualsEndEquals() {

    LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2013-08-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> datas = orbitsDataService.getCartesianPositionsForPeriod(start, end,
        EphemerideType.igs, OrbitType.sp3);

    assertNotNull(datas);
    assertEquals(96, datas.size());
  }

  @Test
  public void testReadDatasForPeriodStartEqualsEndBefore() {

    LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2013-08-22T15:45", DateTimeFormatter.ISO_DATE_TIME);

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> datas = orbitsDataService.getCartesianPositionsForPeriod(start, end,
        EphemerideType.igs, OrbitType.sp3);

    assertNotNull(datas);
    assertEquals(63, datas.size());
  }

  @Test
  public void testReadDatasForPeriodStartAfterEndEquals() {

    LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2013-08-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> datas = orbitsDataService.getCartesianPositionsForPeriod(start, end,
        EphemerideType.igs, OrbitType.sp3);

    assertNotNull(datas);
    assertEquals(55, datas.size());
  }
}


