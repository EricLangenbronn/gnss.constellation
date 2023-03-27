package fr.gnss.constellation.ouranos.librairy.coordinate;

/**
 * Les coordonnées sphériques, sont utilisées pour décrire la position d'un point dans l'espace
 * tridimensionnel en général. Elles sont basées sur la géométrie sphérique et comprennent la distance radiale,
 * l'angle de polarité et l'angle d'azimut. La distance radiale mesure la distance entre le point et l'origine du
 * système de coordonnées, l'angle de polarité mesure l'angle entre le vecteur position du point et l'axe z du système
 * de coordonnées, et l'angle d'azimut mesure l'angle entre le plan xy et le vecteur position du point projeté
 * sur ce plan.
 */
public class SphericalCoordinate implements ICoordinate {

  /**
   * Radial distance r (distance to origin)
   */
  private final double radialDistance;

  /**
   * Polar angle θ (theta) (angle with respect to polar axis)
   * For θ, the range [0°, 180°] for inclination is equivalent to [−90°, +90°] for elevation.
   * In geography, the latitude is the elevation [−90°, +90°].
   */
  private final double polarAngle;

  /**
   * Azimuthal angle φ (phi)
   */
  private final double azimuthalAngle;

  /**
   * Constructs a newly allocated SphericalCoordinate object that represents the
   * specified double values.
   *
   * @param radialDistance - radial distance
   * @param polarAngle     - polar angle
   * @param azimuthalAngle - azimuthal angle
   */
  public SphericalCoordinate(double radialDistance, double polarAngle, double azimuthalAngle) {

    if (radialDistance < 0.0) {
      throw new IllegalArgumentException(
          String.format("Attention la distance radial est inférieur à zéro [radialDistance=%s]", radialDistance));
    }
    this.radialDistance = radialDistance;

    if ((polarAngle < -(Math.PI / 2.0)) || (polarAngle > (Math.PI / 2.0))) {
      throw new IllegalArgumentException(
          String.format("L'inclinaison (latitude) doit être compris entre -90 et 90 degrée [azimuthalAngle=%s]"
              , Math.toDegrees(polarAngle))
      );
    }
    this.polarAngle = polarAngle;

    if ((azimuthalAngle < -(Math.PI)) || (azimuthalAngle) > (Math.PI)) {
      throw new IllegalArgumentException(String.format("L'azimuth (longitude) doit être compris entre -180 et 180 degrée [polarAngle=%s]"
          , Math.toDegrees(azimuthalAngle)));
    }
    this.azimuthalAngle = azimuthalAngle;
  }

  /**
   * Constructs a newly allocated SphericalCoordinate object that represents the
   * specified array of double.
   *
   * @param p - coordinates list
   */
  public SphericalCoordinate(double... p) {
    this(p[0], p[1], p[2]);
  }

  /**
   * Initializes a newly created SphericalCoordinate object so that it represents
   * the same position of SphericalCoordinate as the argument; in other words, the
   * newly created SphericalCoordinate is a copy of the argument
   * SphericalCoordinate.
   *
   * @param p - A GeodeticCoordinate
   */
  public SphericalCoordinate(SphericalCoordinate p) {
    this(p.getRadialDistance(), p.getPolarAngle(), p.getAzimuthAngle());
  }

  /**
   * The radius or radial distance is the Euclidean distance from the origin O to
   * P.
   *
   * @return the radius or radial
   */
  public double getRadialDistance() {
    return this.radialDistance;
  }

  /**
   * The inclination (or polar angle) is the angle between the zenith direction
   * and the line segment OP.
   *
   * @return the polar angle
   */
  public double getPolarAngle() {
    return this.polarAngle;
  }

  /**
   * The azimuth (or azimuthal angle) is the signed angle measured from the
   * azimuth reference direction to the orthogonal projection of the line segment
   * OP on the reference plane.
   *
   * @return the azimuthal angle
   */
  public double getAzimuthAngle() {
    return this.azimuthalAngle;
  }

  @Override
  public double[] getPosition() {
    double[] position = new double[3];
    position[0] = radialDistance;
    position[1] = polarAngle;
    position[2] = azimuthalAngle;

    return position;
  }

  @Override
  public int getDimensions() {
    return 3;
  }

  @Override
  public String toString() {
    return "SphericalCoordinate [distance=" + radialDistance + ", polarAngle=" + polarAngle + ", azimuthalAngle=" + azimuthalAngle + "]";
  }

}
