package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class PolarCoordinate {

	private double r;
	private double theta;

	/**
	 * Initializes a newly created PolarCoordinate object.
	 */
	public PolarCoordinate() {
		r = 0;
		theta = 0;
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
