package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class PolarCoordinate implements ICoordinate {

  /**
   * distance
   */
  private double radialDistance;

  /**
   * angle
   */
  private double theta;

  /**
   * Initializes a newly created PolarCoordinate object.
   */
  public PolarCoordinate() {
    radialDistance = 0;
    theta = 0;
  }

  /**
   * Build a PolarCoordinate transformer from values.
   */
  public PolarCoordinate(double radialDistance, double theta) {
    this.radialDistance = radialDistance;
    this.theta = theta;
  }

  /**
   * Build a PolarCoordinate transformer from CartesianCoordinate3D.
   *
   * @param c - A CartesianCoordinate3D
   */
  public PolarCoordinate(CartesianCoordinate3D c) {
    this();

    Objects.requireNonNull(c, "CartesianCoordinate3D");

    radialDistance = Math.sqrt(Math.pow(c.getAbscisse(), 2) + Math.pow(c.getOrdonnee(), 2));
    theta = Math.atan2(c.getOrdonnee(), c.getAbscisse());
  }

  /**
   * Get the radius.
   *
   * @return the radius r
   */
  public double getRadialDistance() {
    return radialDistance;
  }

  @Override
  public double[] getPosition() {
    double[] position = new double[2];
    position[0] = this.radialDistance;
    position[1] = this.theta;

    return position;
  }

  @Override
  public int getDimensions() {
    return 2;
  }

  /**
   * Get the azimuthal angle in x-y plane.
   *
   * @return the azimuthal angle in x-y plane
   */
  public double getTheta() {
    return theta;
  }

  @Override
  public String toString() {
    return "PolarCoordinate [r=" + radialDistance + ", theta=" + theta + "]";
  }

}
