package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class PolarCoordinate implements ICoordinate {

	/**
	 * distance
	 */
	private double r;

	/**
	 * angle
	 *
	 */
	private double theta;

	/**
	 * Initializes a newly created PolarCoordinate object.
	 */
	public PolarCoordinate() {
		r = 0;
		theta = 0;
	}

	/**
	 * Build a PolarCoordinate transformer from values.
	 */
	public PolarCoordinate(double r, double theta) {
		this.r = r;
		this.theta = theta;
	}

	/**
	 * Build a PolarCoordinate transformer from CartesianCoordinate3D.
	 * 
	 * @param c
	 *            - A CartesianCoordinate3D
	 */
	public PolarCoordinate(CartesianCoordinate3D c) {
		this();

		Objects.requireNonNull(c, "CartesianCoordinate3D");

		r = Math.sqrt(Math.pow(c.X(), 2) + Math.pow(c.Y(), 2));
		theta = Math.atan2(c.Y(), c.X());
	}

	/**
	 * Get the radius.
	 * 
	 * @return the radius r
	 */
	public double getR() {
		return r;
	}

	@Override
	public double[] getPosition() {
		double[] position = new double[2];
		position[0] = this.r;
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
		return "PolarCoordinate [r=" + r + ", theta=" + theta + "]";
	}

}
