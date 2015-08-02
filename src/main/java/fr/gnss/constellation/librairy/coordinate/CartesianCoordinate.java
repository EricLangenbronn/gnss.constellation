package fr.gnss.constellation.librairy.coordinate;

import java.util.Arrays;

public class CartesianCoordinate {

	/**
	 * Position est représenté par un vecteur 3
	 * 
	 */
	private double[] position;

	/**
	 * Création d'un vecteur 3 dont l'origine est le centre de la terre
	 */
	public CartesianCoordinate() {
		position = new double[3];
	}

	public CartesianCoordinate(double x, double y, double z) {
		this();
		position[0] = x;
		position[1] = y;
		position[2] = z;
	}

	public CartesianCoordinate(double[] p) {
		this();
		if (p.length != 3) {
			throw new IllegalArgumentException(
					"Une position doit posséder trois valeurs (longitude, latitude, hauteur)");
		}

		position = Arrays.copyOf(p, 3);
	}

	public CartesianCoordinate(CartesianCoordinate p) {
		this();

		position[0] = p.X();
		position[1] = p.Y();
		position[2] = p.Z();

	}

	/**
	 * Soustraction de deux vecteurs : v2 - v1
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static CartesianCoordinate minus(CartesianCoordinate v1,
			CartesianCoordinate v2) {
		double x = v2.X() - v1.X();
		double y = v2.Y() - v1.Y();
		double z = v2.Z() - v2.Z();

		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * Longeur du vecteur courant.
	 * 
	 * @return
	 */
	public double magnitude() {
		return Math.sqrt(Math.pow(position[0], 2) + Math.pow(position[1], 2)
				+ Math.pow(position[2], 2));
	}

	/**
	 * Transformation du vecteur courant en vecteur unitaire. v.norme() = 1
	 */
	public void normalize() {
		double norme = this.magnitude();

		position[0] = position[0] / norme;
		position[1] = position[1] / norme;
		position[2] = position[2] / norme;

	}

	/**
	 * Crée un nouveau vecteur identique au vecteur courant, transformation du
	 * vecteur crée en vecteur untaire.
	 * 
	 * @return
	 */
	public CartesianCoordinate normalized() {
		CartesianCoordinate res = new CartesianCoordinate(this);
		res.normalize();
		return res;
	}

	/**
	 * Produit scalaire de deux vecteurs.
	 * 
	 * @param c
	 * @return
	 */
	public double dotProduct(CartesianCoordinate c) {
		return position[0] * c.X() + position[1] * c.Y()
				+ position[2] * c.Z();
	}

	/**
	 * Calcul de l'angle entre deux vecteurs. Angle en degré.
	 * 
	 * @param c
	 * @return
	 */
	public double angle(CartesianCoordinate c) {
		return Math.acos(this.dotProduct(c) / (this.magnitude() * c.magnitude()));
	}

	public double X() {
		return position[0];
	}

	public double Y() { // profondeur
		return position[1];
	}

	public double Z() {
		return position[2];
	}

	public double[] getPosition() {
		return position;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof CartesianCoordinate) {
			CartesianCoordinate anotherPosition = (CartesianCoordinate) obj;
			if ((this.X() == anotherPosition.X())
					&& (this.Y() == anotherPosition.Y())
					&& (this.Z() == anotherPosition.Z())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "CoordinateCartesian [position=" + Arrays.toString(position)
				+ "]";
	}
}
