package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SphericalCoordinate implements ICoordinate {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(SphericalCoordinate.class);

	/**
	 * r : radial distance
	 */
	private double distance;

	/**
	 * θ : polar angle (theta)
	 */
	private double longitude;

	/**
	 * φ : azimuthal angle
	 */
	private double latitude;

	/**
	 * Initializes a newly created SphericalCoordinate object.
	 */
	public SphericalCoordinate() {
		this.distance = 0;
		this.longitude = 0;
		this.latitude = 0;
	}

	/**
	 * Constructs a newly allocated SphericalCoordinate object that represents
	 * the specified double values.
	 * 
	 * @param radiusDistance
	 *            - radial distance
	 * @param theta
	 *            - longitude
	 * @param phi
	 *            - latitude
	 */
	public SphericalCoordinate(double radiusDistance, double theta, double phi) {
		this.distance = radiusDistance;
		this.longitude = theta;
		this.latitude = phi;

		// validate();
	}

	/**
	 * Constructs a newly allocated SphericalCoordinate object that represents
	 * the specified array of double.
	 * 
	 * @param p
	 *            - coordinates list
	 */
	public SphericalCoordinate(double... p) {
		this();
		if (p.length != 3) {
			throw new IllegalArgumentException(
					"Une position doit posséder trois valeurs (radial distance, polar, azimuth)");
		}

		this.distance = p[0];
		this.longitude = p[1];
		this.latitude = p[2];
	}

	/**
	 * Initializes a newly created SphericalCoordinate object so that it
	 * represents the same position of SphericalCoordinate as the argument; in
	 * other words, the newly created SphericalCoordinate is a copy of the
	 * argument SphericalCoordinate.
	 * 
	 * @param p
	 *            - A GeodeticCoordinate
	 */
	public SphericalCoordinate(SphericalCoordinate p) {
		this();

		Objects.requireNonNull(p, "SphericalCoordinate");

		this.distance = p.getRadiusDistance();
		this.longitude = p.getAzimuth();
		this.latitude = p.getInclination();

		// validate();
	}

	/**
	 * r ≥ 0 0° ≤ θ ≤ 180° (π rad) 0° ≤ φ < 360° (2π rad)
	 */
	private void validate() {
		if (this.distance < 0) {
			LOGGER.info("Attention la distance radial est inférieur à zéro");
		}

		/** inclinaison compris entre -90 et 90 degrée */
		if ((this.latitude < -(Math.PI / 2)) || (this.latitude > (Math.PI / 2))) {
			LOGGER.info("L'inclinaison (latitude) doit être compris entre -90 et 90 degrée");
		}

		if ((this.longitude < -(Math.PI)) || (this.longitude) > (Math.PI)) {
			LOGGER.info("L'azimuth (longitude) doit être compris entre -180 et 180 degrée");
		}
	}

	/**
	 * The radius or radial distance is the Euclidean distance from the origin O
	 * to P.
	 * 
	 * @return the radius or radial
	 */
	public double getRadiusDistance() {
		return this.distance;
	}

	/**
	 * The inclination (or polar angle) is the angle between the zenith
	 * direction and the line segment OP.
	 * 
	 * @return the polar angle
	 */
	public double getInclination() {
		return this.latitude;
	}

	/**
	 * The azimuth (or azimuthal angle) is the signed angle measured from the
	 * azimuth reference direction to the orthogonal projection of the line
	 * segment OP on the reference plane.
	 * 
	 * @return the azimuthal angle
	 */
	public double getAzimuth() {
		return this.longitude;
	}

	@Override
	public double[] getPosition() {
		double[] position = new double[3];
		position[0] = distance;
		position[1] = longitude;
		position[2] = latitude;

		return position;
	}

	@Override
	public int getDimensions() {
		return 3;
	}

	@Override
	public String toString() {
		return "SphericalCoordinate [distance=" + distance + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}

}
