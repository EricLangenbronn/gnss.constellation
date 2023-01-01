package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class SphericalCoordinate implements ICoordinate {

    /**
     * r : radial distance
     */
    private double radialDistance;

    /**
     * θ : polar angle (theta)
     */
    private double polarAngle;

    /**
     * φ : azimuthal angle
     */
    private double azimuthalAngle;

    /**
     * Initializes a newly created SphericalCoordinate object.
     */
    public SphericalCoordinate() {
        this.radialDistance = 0;
        this.polarAngle = 0;
        this.azimuthalAngle = 0;
    }

    /**
     * Constructs a newly allocated SphericalCoordinate object that represents the
     * specified double values.
     *
     * @param radiusDistance - radial distance
     * @param theta          - longitude
     * @param phi            - latitude
     */
    public SphericalCoordinate(double radiusDistance, double theta, double phi) {
        this.radialDistance = radiusDistance;
        this.polarAngle = theta;
        this.azimuthalAngle = phi;

        validate();
    }

    /**
     * Constructs a newly allocated SphericalCoordinate object that represents the
     * specified array of double.
     *
     * @param p - coordinates list
     */
    public SphericalCoordinate(double... p) {
        this();
        if (p.length != 3) {
            throw new IllegalArgumentException("Une position doit posséder trois valeurs (radial distance, polar, azimuth)");
        }

        this.radialDistance = p[0];
        this.polarAngle = p[1];
        this.azimuthalAngle = p[2];

        validate();
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
        this();

        Objects.requireNonNull(p, "SphericalCoordinate");

        this.radialDistance = p.getRadiusDistance();
        this.polarAngle = p.getInclination();
        this.azimuthalAngle = p.getAzimuth();

        validate();
    }

    /**
     * r ≥ 0 0° ≤ θ ≤ 180° (π rad) 0° ≤ φ < 360° (2π rad)
     */
    private void validate() {
        if (this.radialDistance < 0.0) {
            throw new IllegalArgumentException(String.format("Attention la distance radial est inférieur à zéro [radialDistance=%s]", radialDistance));
        }
        
        if ((this.polarAngle < -(Math.PI / 2.0)) || (this.polarAngle > (Math.PI / 2.0))) {
            throw new IllegalArgumentException(String.format("L'inclinaison (latitude) doit être compris entre -90 et 90 degrée [azimuthalAngle=%s]", Math.toDegrees(polarAngle)));
        }

        if ((this.azimuthalAngle < -(Math.PI)) || (this.azimuthalAngle) > (Math.PI)) {
            throw new IllegalArgumentException(String.format("L'azimuth (longitude) doit être compris entre -180 et 180 degrée [polarAngle=%s]", Math.toDegrees(azimuthalAngle)));
        }
    }

    /**
     * The radius or radial distance is the Euclidean distance from the origin O to
     * P.
     *
     * @return the radius or radial
     */
    public double getRadiusDistance() {
        return this.radialDistance;
    }

    /**
     * The inclination (or polar angle) is the angle between the zenith direction
     * and the line segment OP.
     *
     * @return the polar angle
     */
    public double getInclination() {
        return this.polarAngle;
    }

    /**
     * The azimuth (or azimuthal angle) is the signed angle measured from the
     * azimuth reference direction to the orthogonal projection of the line segment
     * OP on the reference plane.
     *
     * @return the azimuthal angle
     */
    public double getAzimuth() {
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
        return "SphericalCoordinate [distance=" + radialDistance + ", longitude=" + polarAngle + ", latitude=" + azimuthalAngle + "]";
    }

}
