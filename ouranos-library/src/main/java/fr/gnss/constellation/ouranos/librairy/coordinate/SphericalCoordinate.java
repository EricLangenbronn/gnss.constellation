package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SphericalCoordinate {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(SphericalCoordinate.class);

	/**
	 * Position est représenté par un vecteur 3
	 * 
	 * r : radial distance θ : polar angle (theta) φ : azimuthal angle (phi)
	 */
	private double[] position;

	/**
	 * Initializes a newly created SphericalCoordinate object.
	 */
	public SphericalCoordinate() {
		position = new double[3];
	}

	/**
	 * Constructs a newly allocated SphericalCoordinate object that represents
	 * the specified double values.
	 * 
	 * @param radiusDistance
	 *            - radial distance
	 * @param theta
	 *            - polar angle
	 * @param phi
	 *            - azimuthal angle
	 */
	public SphericalCoordinate(double radiusDistance, double theta, double phi) {
		this();
		position[0] = radiusDistance;
		position[1] = theta;
		position[2] = phi;

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

		position = Arrays.copyOf(p, 3);

		// validate();
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

		position[0] = p.getRadiusDistance();
		position[1] = p.getInclination();
		position[2] = p.getAzimuth();

		// validate();
	}

	/**
	 * r ≥ 0 0° ≤ θ ≤ 180° (π rad) 0° ≤ φ < 360° (2π rad)
	 */
	private void validate() {
		if (position[0] < 0) {
			LOGGER.info("Attention la distance radial est inférieur à zéro");
		}

		/** inclinaison compris entre -90 et 90 degrée */
		if ((position[1] < -(Math.PI / 2)) || (position[1] > (Math.PI / 2))) {
			LOGGER.info("L'inclinaison doit être compris entre -90 et 90 degrée");
		}

		if ((position[2] < -(Math.PI)) || (position[2]) > (Math.PI)) {
			LOGGER.info("L'azimuth doit être compris entre -180 et 180 degrée");
		}
	}

	/**
	 * The radius or radial distance is the Euclidean distance from the origin O
	 * to P.
	 * 
	 * @return the radius or radial
	 */
	public double getRadiusDistance() {
		return position[0];
	}

	/**
	 * The inclination (or polar angle) is the angle between the zenith
	 * direction and the line segment OP.
	 * 
	 * @return the polar angle
	 */
	public double getInclination() {
		return position[1];
	}

	/**
	 * The azimuth (or azimuthal angle) is the signed angle measured from the
	 * azimuth reference direction to the orthogonal projection of the line
	 * segment OP on the reference plane.
	 * 
	 * @return the azimuthal angle
	 */
	public double getAzimuth() {
		return position[2];
	}

	@Override
	public String toString() {
		return "SphericalCoordinate [position=" + Arrays.toString(position) + "]";
	}

}
