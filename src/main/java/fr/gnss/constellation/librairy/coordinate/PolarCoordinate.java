package fr.gnss.constellation.librairy.coordinate;

public class PolarCoordinate {

	private double r;
	private double theta;

	public PolarCoordinate() {
		r = 0;
		theta = 0;
	}

	public PolarCoordinate(CartesianCoordinate3D c) {
		this();
		r = Math.sqrt(Math.pow(c.X(), 2)
				+ Math.pow(c.Y(), 2));
		theta = Math.atan2(c.Y(), c.X());
	}

	public double getR() {
		return r;
	}

	public double getTheta() {
		return theta;
	}

	@Override
	public String toString() {
		return "PolarCoordinate [r=" + r + ", theta=" + theta + "]";
	}

}
