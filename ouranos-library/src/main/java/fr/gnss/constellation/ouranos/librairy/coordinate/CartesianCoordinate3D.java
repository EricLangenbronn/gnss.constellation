package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class implements vectors in a three-dimensional space.
 */
public class CartesianCoordinate3D {

	/**
	 * [0] - Abscissa (X). [1] - Ordinate (Y). [2] - Height (Z).
	 */
	private double[] position;

	/**
	 * Initializes a newly created CartesianCoordinate3D object.
	 */
	public CartesianCoordinate3D() {
		position = new double[3];
	}

	/**
	 * Constructs a newly allocated CartesianCoordinate3D object that represents
	 * the specified double values.
	 * 
	 * @param x
	 *            - abscissa
	 * @param y
	 *            - ordinate
	 * @param z
	 *            - height
	 */
	public CartesianCoordinate3D(double x, double y, double z) {
		this();
		position[0] = x;
		position[1] = y;
		position[2] = z;
	}

	/**
	 * Constructs a newly allocated CartesianCoordinate3D object that represents
	 * the specified array of double.
	 * 
	 * @param p
	 *            - coordinates list
	 */
	public CartesianCoordinate3D(double... p) {
		this();
		if (p.length != 3) {
			throw new IllegalArgumentException(
					"Une position doit posséder trois valeurs (longitude, latitude, hauteur)");
		}

		position = Arrays.copyOf(p, 3);
	}

	/**
	 * Initializes a newly created CartesianCoordinate3D object so that it
	 * represents the same position of CartesianCoordinate3D as the argument; in
	 * other words, the newly created CartesianCoordinate3D is a copy of the
	 * argument CartesianCoordinate3D.
	 * 
	 * @param p
	 *            - A CartesianCoordinate3D
	 */
	public CartesianCoordinate3D(CartesianCoordinate3D p) {
		this();

		Objects.requireNonNull(p, "CartesianCoordinate3D");

		position[0] = p.X();
		position[1] = p.Y();
		position[2] = p.Z();

	}

	/**
	 * Subtract a CartesianCoordinate3D from the other. (v1 - v2)
	 * 
	 * @param v1
	 *            -
	 * @param v2
	 *            - CartesianCoordinate3D to subtract
	 * @return the specified new CartesianCoordinate3D
	 */
	public static CartesianCoordinate3D minus(CartesianCoordinate3D v1, CartesianCoordinate3D v2) {
		Objects.requireNonNull(v1, "CartesianCoordinate3D");
		Objects.requireNonNull(v2, "CartesianCoordinate3D");

		double x = v1.X() - v2.X();
		double y = v1.Y() - v2.Y();
		double z = v1.Z() - v2.Z();

		return new CartesianCoordinate3D(x, y, z);
	}

	/**
	 * Get the norm of the instance.
	 * 
	 * @return the norm of the instance.
	 */
	public double magnitude() {
		return Math.sqrt(Math.pow(position[0], 2) + Math.pow(position[1], 2) + Math.pow(position[2], 2));
	}

	/**
	 * Normalized CartesianCoordinate3D of the instance.
	 */
	public void normalize() {
		double norme = this.magnitude();

		position[0] = position[0] / norme;
		position[1] = position[1] / norme;
		position[2] = position[2] / norme;

	}

	/**
	 * Get a normalized vector aligned with the instance.
	 * 
	 * @return a new normalized CartesianCoordinate3D
	 */
	public CartesianCoordinate3D normalized() {
		CartesianCoordinate3D res = new CartesianCoordinate3D(this);
		res.normalize();
		return res;
	}

	/**
	 * Compute the dot-product of the instance with an other
	 * CartesianCoordinate3D.
	 * 
	 * @param c
	 *            - A CartesianCoordinate3D
	 * @return the dot product
	 */
	public double dotProduct(CartesianCoordinate3D c) {
		Objects.requireNonNull(c, "CartesianCoordinate3D");
		return position[0] * c.X() + position[1] * c.Y() + position[2] * c.Z();
	}

	/**
	 * Compute the angular separation of the instance with an other
	 * CartesianCoordinate3D.
	 * 
	 * @param c
	 *            - A CartesianCoordinate3D
	 * @return angular separation of the instance with c
	 */
	public double angle(CartesianCoordinate3D c) {
		Objects.requireNonNull(c, "CartesianCoordinate3D");
		return Math.acos(this.dotProduct(c) / (this.magnitude() * c.magnitude()));
	}

	/**
	 * Get the abscissa of the vector.
	 * 
	 * @return the abscissa of the vector
	 */
	public double X() {
		return position[0];
	}

	/**
	 * Get the ordinate of the vector.
	 * 
	 * @return the ordinate of the vector
	 */
	public double Y() { // profondeur
		return position[1];
	}

	/**
	 * Get the height of the vector.
	 * 
	 * @return the height of the vector
	 */
	public double Z() {
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof CartesianCoordinate3D) {
			CartesianCoordinate3D anotherPosition = (CartesianCoordinate3D) obj;
			if ((this.X() == anotherPosition.X()) && (this.Y() == anotherPosition.Y())
					&& (this.Z() == anotherPosition.Z())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "CoordinateCartesian [position=" + Arrays.toString(position) + "]";
	}
}
