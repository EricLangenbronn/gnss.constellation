package fr.gnss.constellation.ouranos.librairy.coordinate;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoordinateTransformation {

  /**
   * WGS 84 (World Geodetic System 1984 : système géodésique mondial, révision
   * de 1984) est le système géodésique mondial le plus courant, car il est
   * utilisé par le système GPS.
   */
  public static double a = 6378137.0000; // demi grand axe en metre
  public static double b = 6356752.3142; // demi petit axe en metre
  public static double ee = 1 - (Math.pow((CoordinateTransformation.b / CoordinateTransformation.a), 2));

  /**
   * Convert geodetic to geocentric (ECEF) coordinates.
   *
   * @param latitude  - latitude in radian
   * @param longitude - longitude in radian
   * @param heights   - altitude in radian
   * @return A Cartesian coordinate, that is a 3D vector
   */
  public static CartesianCoordinate3D geodeticToCartesianWsg84(double latitude, double longitude, double heights) { // TODO : Test Ok
    Objects.requireNonNull(latitude, "phi");
    Objects.requireNonNull(longitude, "lambda");
    Objects.requireNonNull(heights, "heights");

    double n = CoordinateTransformation.a / Math.sqrt(1.0 - ee * (Math.sin(latitude) * Math.sin(latitude)));

    double x = (n + heights) * Math.cos(latitude) * Math.cos(longitude);
    double y = (n + heights) * Math.cos(latitude) * Math.sin(longitude);
    double z = ((n * (1 - ee)) + heights) * Math.sin(latitude);

    return new CartesianCoordinate3D(x, y, z);
  }

  /**
   * To transform from ECEF coordinates to the local coordinates we need a
   * local reference point, typically this might be the location of a radar.
   * If a radar is located at {X_r, Y_r, Z_r} and an aircraft at {X_p, Y_p,
   * Z_p} then the vector pointing from the radar to the aircraft in the ENU
   * frame is
   * <p>
   * /!\ Attention Note: phi is the geodetic latitude. Note: Unambiguous
   * determination of phi and lambda requires knowledge of which quadrant the
   * coordinates lie in.
   *
   * @param latitude  - latitude (en radian)
   * @param longitude - longitude (en radian)
   * @param vector    - vector 3D to rotate
   * @return A 3D vector North, East, Up
   */

  // Note: easting (E), northing (N), upwardness (U). Local azimuth angle would be measured
  public static double[] transformEceftoNeu(double latitude, double longitude, CartesianCoordinate3D vector) { // TODO : Test Ok
    Objects.requireNonNull(vector, "CartesianCoordinate3D");

    // North
    double n1 = ((-Math.sin(latitude)) * (Math.cos(longitude))) * vector.getAbscisse();
    double n2 = ((-(Math.sin(latitude))) * Math.sin(longitude)) * vector.getOrdonnee();
    double n3 = Math.cos(latitude) * vector.getCote();
    double n4 = n1 + n2 + n3;

    // East
    double e1 = -(Math.sin(longitude)) * vector.getAbscisse();
    double e2 = Math.cos(longitude) * vector.getOrdonnee();
    double e4 = e1 + e2;

    // Up
    double u1 = (Math.cos(longitude) * Math.cos(latitude)) * vector.getAbscisse();
    double u2 = (Math.sin(longitude) * Math.cos(latitude)) * vector.getOrdonnee();
    double u3 = Math.sin(latitude) * vector.getCote();
    double u4 = u1 + u2 + u3;

    double[] res = {n4, e4, u4};

    return res;
  }

  public static SphericalCoordinate processElevationAzimuth(GeodeticCoordinate groundStation, CartesianCoordinate3D satelite) {

    CartesianCoordinate3D stationWsf84 = CoordinateTransformation.geodeticToCartesianWsg84(groundStation.getLatitude()
        , groundStation.getLongitude(), groundStation.getAltitude());
    CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite, stationWsf84);
    CartesianCoordinate3D stationSateliteNorm = stationSatelite.normalized();

    double[] enuStationSatelite = CoordinateTransformation.transformEceftoNeu(groundStation.getLatitude()
        , groundStation.getLongitude(), stationSateliteNorm);

    double normeStaSat = Math.sqrt(Math.pow(enuStationSatelite[0], 2) + Math.pow(enuStationSatelite[1], 2)
        + Math.pow(enuStationSatelite[2], 2));

    return new SphericalCoordinate(normeStaSat, Math.asin(-enuStationSatelite[2] / normeStaSat)
        , Math.atan2(enuStationSatelite[0] / normeStaSat, enuStationSatelite[1] / normeStaSat));
  }

  public static TimeCoordinateSatellitePosition<SphericalCoordinate> transformCartesionToSpherical(
      TimeCoordinateSatellitePosition<CartesianCoordinate3D> timeCoordinateSatellitePositionCartesian, GeodeticCoordinate groundStation) {

    TimeCoordinateSatellitePosition<SphericalCoordinate> timeCoordinateSatellitePositionSpherical =
        new TimeCoordinateSatellitePosition<SphericalCoordinate>(timeCoordinateSatellitePositionCartesian.getEpochHeaderRecord());
    for (SatellitePosition<CartesianCoordinate3D> satellitePositionCartesianCoordinate : timeCoordinateSatellitePositionCartesian.getSatellites()
        .values()) {
      SphericalCoordinate sphCoord =
          CoordinateTransformation.processElevationAzimuth(groundStation, satellitePositionCartesianCoordinate.getPosition());
      SatellitePosition<SphericalCoordinate> satellitePositionSpherical =
          new SatellitePosition<>(satellitePositionCartesianCoordinate.getVehicleId(), sphCoord);


      timeCoordinateSatellitePositionSpherical.addSatellite(satellitePositionSpherical.getVehicleId(), satellitePositionSpherical);
    }

    return timeCoordinateSatellitePositionSpherical;
  }

  public static List<TimeCoordinateSatellitePosition<SphericalCoordinate>> transformCartesionsToSphericals(
      List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> timeCoordinateSatellitesPositionsCartesian
      , GeodeticCoordinate groundStation) {

    List<TimeCoordinateSatellitePosition<SphericalCoordinate>> timeCoordinateSatellitesPositionsSpherical = new ArrayList<>();
    for (TimeCoordinateSatellitePosition<CartesianCoordinate3D> timeCoordinateSatellitePositionCartesian : timeCoordinateSatellitesPositionsCartesian) {
      timeCoordinateSatellitesPositionsSpherical.add(
          transformCartesionToSpherical(timeCoordinateSatellitePositionCartesian, groundStation));
    }

    return timeCoordinateSatellitesPositionsSpherical;
  }
}
