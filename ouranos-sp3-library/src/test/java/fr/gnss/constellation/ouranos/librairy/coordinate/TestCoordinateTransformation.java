package fr.gnss.constellation.ouranos.librairy.coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestCoordinateTransformation {

  @Test
  public void testGeodeticToCartesianStrasbourg() { // TODO : Test Ok

    CartesianCoordinate3D cartesianPosition =
        CoordinateTransformation.geodeticToCartesianWsg84(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);

    assertEquals(4188756.20483369, cartesianPosition.getAbscisse(), 0.0001);
    assertEquals(569731.86031346, cartesianPosition.getOrdonnee(), 0.0001);
    assertEquals(4760166.05125538, cartesianPosition.getCote(), 0.0001);

  }

  @Test
  public void testGeodeticToCartesianParis() { // TODO : Test Ok

    CartesianCoordinate3D cartesianPosition =
        CoordinateTransformation.geodeticToCartesianWsg84(Math.toRadians(48.8534100), Math.toRadians(2.3488000), 120.0);

    assertEquals(4201270.83938198, cartesianPosition.getAbscisse(), 0.0001);
    assertEquals(172324.672938237, cartesianPosition.getOrdonnee(), 0.0001);
    assertEquals(4779938.29153465, cartesianPosition.getCote(), 0.0001);

  }

  @Test
  public void testTransformECEFtoNeuStrasbourg() { // TODO : Test Ok

    CartesianCoordinate3D cartesiantStationSatelite = new CartesianCoordinate3D(4201270.83938198, 172324.672938237, 4779938.29153465);

    double[] resEver = CoordinateTransformation.transformEceftoNeu(Math.toRadians(48.5839200), Math.toRadians(7.7455300),
        cartesiantStationSatelite);


    assertEquals(22724.3945970642, resEver[0], 0.0001);
    assertEquals(-395468.0517734572, resEver[1], 0.0001);
    assertEquals(6353839.0876335958, resEver[2], 0.0001);
  }

  @Test
  public void testCalcElevationAzimuthSat() {

    GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);
    CartesianCoordinate3D cartesianStation =
        CoordinateTransformation.geodeticToCartesianWsg84(station.getLatitude(), station.getLongitude(), station.getAltitude());

    assertEquals(4188756.20483369, cartesianStation.getAbscisse(), 0.0001);
    assertEquals(569731.86031346, cartesianStation.getOrdonnee(), 0.0001);
    assertEquals(4760166.05125538, cartesianStation.getCote(), 0.0001);

    double[] positionECEFSat = {5441177.312, -25836148.765, -2502546.747};

    CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(new CartesianCoordinate3D(positionECEFSat),
        new CartesianCoordinate3D(cartesianStation));

    assertEquals(1252421.1071663070, stationSatelite.getAbscisse(), 0.0001);
    assertEquals(-26405880.6253134608, stationSatelite.getOrdonnee(), 0.0001);
    assertEquals(-7262712.7983230464, stationSatelite.getCote(), 0.0001);

    double[] enuStationSateliteVector = CoordinateTransformation.transformEceftoNeu(station.getLatitude(), station.getLongitude(),
        stationSatelite);

    assertEquals(-3066253.3858688683, enuStationSateliteVector[0], 0.0001);
    assertEquals(-26333757.6613142528, enuStationSateliteVector[1], 0.0001);
    assertEquals(-6979784.0244327448, enuStationSateliteVector[2], 0.0001);

    CartesianCoordinate3D enuStationSatelite = new CartesianCoordinate3D(enuStationSateliteVector);

    double normeStaSat = enuStationSatelite.magnitude();

    // Angle d'élévation
    double elevation = Math.asin(-enuStationSatelite.getCote() / normeStaSat);
    assertEquals(0.2574305456, elevation, 0.0001);

    // Angle azimute
    double azimute = Math.atan2(enuStationSatelite.getAbscisse() / normeStaSat, enuStationSatelite.getOrdonnee() / normeStaSat);
    assertEquals(-3.0256765046, azimute, 0.0001);

  }

  @Test
  public void testCalcElevationAzimuthSatUseLibrairie() {

    GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);
    CartesianCoordinate3D satelite = new CartesianCoordinate3D(5441177.312, -25836148.765, -2502546.747);

    SphericalCoordinate angles = CoordinateTransformation.processElevationAzimuth(station, satelite);

    // Angle d'élévation
    assertEquals(0.2574305456, angles.getPolarAngle(), 0.0001);
    // Angle azimute
    assertEquals(-3.0256765046, angles.getAzimuthAngle(), 0.0001);
  }

  // TODO : regarder  LG2CT, LG2DG
}
