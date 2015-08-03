package fr.gnss.constellation.librairy.coordinate;

import java.util.Arrays;

/**
 * 
 * 
 * @author Eric
 * 
 *         CoordinateGeographic
 *
 */

public class GeodeticCoordinate {

	/**
	 * Position est représenté par un vecteur 3
	 * 
	 * X : latitude : lambda : radian (NordSud)
	 * Y : longitude : phi : radian (EstOuest)
	 * Z : altitude	: h : metre
	 */
	private double[] position;

	public GeodeticCoordinate() {
		position = new double[3];
	}

	public GeodeticCoordinate(double lambda, double phi, double h) {
		this();
		position[0] = lambda;
		position[1] = phi;
		position[2] = h;
	}

	public GeodeticCoordinate(double... p) {
		this();
		if (p.length != 3) {
			throw new IllegalArgumentException(
					"Une position doit posséder trois valeurs (longitude, latitude, hauteur)");
		}

		position = Arrays.copyOf(p, 3);
	}

	public GeodeticCoordinate(GeodeticCoordinate p) {
		this();
		position[0] = p.getLatitude();
		position[1] = p.getLongitude();
		position[2] = p.getAltitude();
	}

	public double getLatitude() {
		return position[0];
	}

	public double getLongitude() {
		return position[1];
	}

	public double getAltitude() {
		return position[2];
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
