package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */

public class GeodeticCoordinate implements ICoordinate {

	/**
	 * Position est représenté par un vecteur 3
	 * 
	 * X : latitude : phi : radian (NordSud) Y : longitude : lambda : radian
	 * (EstOuest) Z : altitude : h : metre
	 */
	private double[] position;

	/**
	 * Initializes a newly created GeodeticCoordinate object.
	 */
	public GeodeticCoordinate() {
		position = new double[3];
	}

	/**
	 * Constructs a newly allocated GeodeticCoordinate object that represents
	 * the specified double values.
	 * 
	 * @param phi
	 *            - latitude
	 * @param lambda
	 *            - longitude
	 * @param h
	 *            - altitude
	 */
	public GeodeticCoordinate(double phi, double lambda, double h) {
		this();
		position[0] = phi;
		position[1] = lambda;
		position[2] = h;
	}

	/**
	 * Constructs a newly allocated GeodeticCoordinate object that represents
	 * the specified array of double.
	 * 
	 * @param p
	 *            - coordinates list
	 */
	public GeodeticCoordinate(double... p) {
		this();
		if (p.length != 3) {
			throw new IllegalArgumentException(
					"Une position doit posséder trois valeurs (longitude, latitude, hauteur)");
		}

		position = Arrays.copyOf(p, 3);
	}

	/**
	 * Initializes a newly created GeodeticCoordinate object so that it
	 * represents the same position of GeodeticCoordinate as the argument; in
	 * other words, the newly created GeodeticCoordinate is a copy of the
	 * argument GeodeticCoordinate.
	 * 
	 * @param p
	 *            - A GeodeticCoordinate
	 */
	public GeodeticCoordinate(GeodeticCoordinate p) {
		this();

		Objects.requireNonNull(p, "GeodeticCoordinate");

		position[0] = p.getLatitude();
		position[1] = p.getLongitude();
		position[2] = p.getAltitude();
	}

	/**
	 * Get the lambda.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return position[0];
	}

	/**
	 * Get the phi.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return position[1];
	}

	/**
	 * Get the h.
	 * 
	 * @return the altitude
	 */
	public double getAltitude() {
		return position[2];
	}

	/**
	 * Get the vector3D
	 * 
	 * @return vector size 3
	 */
	public double[] getVector() {
		return this.position;
	}

	@Override
	public double[] getPosition() {
		return this.position;
	}

	@Override
	public int getDimensions() {
		return this.position.length;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof GeodeticCoordinate) {
			GeodeticCoordinate anotherPosition = (GeodeticCoordinate) obj;
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
		return "Position [position=" + Arrays.toString(position) + "]";
	}

}
