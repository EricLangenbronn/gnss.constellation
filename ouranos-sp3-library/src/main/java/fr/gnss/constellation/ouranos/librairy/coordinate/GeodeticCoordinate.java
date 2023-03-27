package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

/**
 * GPS Global Positioning System
 * ECEF Earth Centered Earth Fixed
 * ENU East North Up
 * WGS84 World Geodetic System 1984
 */


/**
 * Les coordonnées géodésiques sont utilisées pour décrire la position d'un point sur la surface de la Terre.
 * Elles sont basées sur une référence géodésique, qui est un modèle mathématique de la forme de la Terre.
 * Les coordonnées géodésiques comprennent la latitude, la longitude et l'altitude, qui mesurent respectivement
 * la distance nord-sud, la distance est-ouest et l'élévation par rapport au niveau de la mer.
 */
public class GeodeticCoordinate implements ICoordinate {

  /**
   * latitude : phi : radian (NordSud)
   */
  private final Latitude latitude;

  /**
   * longitude : lambda : radian (EstOuest)
   */
  private final Longitude longitude;

  /**
   * altitude : h : metre
   */
  private final Altitude altitude;


  /**
   * Constructs a newly allocated GeodeticCoordinate object that represents
   * the specified double values.
   *
   * @param phi    - latitude
   * @param lambda - longitude
   * @param h      - altitude
   */
  public GeodeticCoordinate(double phi, double lambda, double h) {

    this.latitude = new Latitude(phi);
    this.longitude = new Longitude(lambda);
    this.altitude = new Altitude(h);
  }

  /**
   * Constructs a newly allocated GeodeticCoordinate object that represents
   * the specified array of double.
   *
   * @param p - coordinates list
   */
  public GeodeticCoordinate(double... p) {
    if (p.length != 3) {
      throw new IllegalArgumentException("Une position doit posséder trois valeurs (longitude, latitude, hauteur)");
    }

    this.latitude = new Latitude(p[0]);
    this.longitude = new Longitude(p[1]);
    this.altitude = new Altitude(p[2]);
  }

  /**
   * Initializes a newly created GeodeticCoordinate object so that it
   * represents the same position of GeodeticCoordinate as the argument; in
   * other words, the newly created GeodeticCoordinate is a copy of the
   * argument GeodeticCoordinate.
   *
   * @param p - A GeodeticCoordinate
   */
  public GeodeticCoordinate(GeodeticCoordinate p) {
    Objects.requireNonNull(p, "GeodeticCoordinate");

    this.latitude = new Latitude(p.getLatitude());
    this.longitude = new Longitude(p.getLongitude());
    this.altitude = new Altitude(p.getAltitude());
  }

  /**
   * Get the lambda.
   *
   * @return the latitude
   */
  public double getLatitude() {
    return this.latitude.getValue();
  }

  /**
   * Get the phi.
   *
   * @return the longitude
   */
  public double getLongitude() {
    return this.longitude.getValue();
  }

  /**
   * Get the h.
   *
   * @return the altitude
   */
  public double getAltitude() {
    return this.altitude.getValue();
  }

  @Override
  public double[] getPosition() {
    return new double[] {this.latitude.getValue(), this.longitude.getValue(), this.altitude.getValue()};
  }

  @Override
  public int getDimensions() {
    return 3;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof GeodeticCoordinate anotherPosition) {
      if ((this.getLatitude() == anotherPosition.getLatitude())
          && (this.getLongitude() == anotherPosition.getLongitude())
          && (this.getAltitude() == anotherPosition.getAltitude())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public String toString() {
    return "Position [latitude=" + this.latitude.getValue() + ",longitude=" + this.longitude.getValue() + ",altitude="
        + this.altitude.getValue() + "]";
  }

  public static class Latitude {
    private final Double latitude;

    public Latitude(Double latitude) {

      if (latitude == null || (latitude < -(Math.PI / 2.0) || latitude > (Math.PI / 2.0))) {
        throw new IllegalArgumentException(
            String.format("Latitude erronée, elle doit être comprise entre [%s,%s] radian : [latitude=%s]"
                , -(Math.PI / 2.0), (Math.PI / 2.0), latitude)
        );
      }

      this.latitude = latitude;
    }

    public Double getValue() {
      return latitude;
    }
  }

  public static class Longitude {
    private final Double longitude;

    public Longitude(Double longitude) {

      if (longitude == null || (longitude < -(Math.PI) || longitude > (Math.PI))) {
        throw new IllegalArgumentException(
            String.format("Longitude erronée, elle doit être comprise entre [%s,%s] radian : [longitude=%s]"
                , -Math.PI, Math.PI, longitude)
        );
      }

      this.longitude = longitude;
    }

    public Double getValue() {
      return longitude;
    }
  }

  public static class Altitude {
    private final Double altitude;

    public Altitude(Double altitude) {

      if (altitude != null) {
        if (altitude < -10909.0) {
          throw new IllegalArgumentException(
              String.format("Altitude erronée, tu n'es pas un passe muraille : [altitude=%s] metre", altitude));
        }
        if (altitude > 8848.0) {
          throw new IllegalArgumentException(String.format("Altitude erronée, tu ne touches plus le sol : [altitude=%s] metre", altitude));
        }
      }

      this.altitude = altitude;
    }

    public Double getValue() {
      return altitude;
    }
  }
}
