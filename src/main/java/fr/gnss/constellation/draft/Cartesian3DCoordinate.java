package fr.gnss.constellation.draft;


public class Cartesian3DCoordinate {

	private Vector vector;

	public Cartesian3DCoordinate() {
		vector = new Vector(3);
	}

	public Cartesian3DCoordinate(double[] p_vector) {
		if (p_vector.length != 3) {
			throw new IllegalArgumentException(
					"Une coordonnée cartesienne 3D ne posséde que trois valeurs (longitude, latitude, hauteur)");
		}

		vector = new Vector(p_vector);
	}

	/*
	 * Ce constructeur est viable tant que Vector est immutable. Il ne sert qu'a
	 * l'encapsulation
	 */
	private Cartesian3DCoordinate(Vector p_vector) {
		vector = p_vector;
	}

	protected Vector getVector() {
		return vector;
	}

	public Cartesian3DCoordinate plus(Cartesian3DCoordinate p_that) {
		return new Cartesian3DCoordinate(vector.plus(getVector()));
	}

	public Cartesian3DCoordinate minus(Cartesian3DCoordinate p_that) {
		return new Cartesian3DCoordinate(vector.minus(getVector()));
	}

	public Cartesian3DCoordinate times(double p_factor) {
		return new Cartesian3DCoordinate(vector.times(p_factor));
	}

	public Cartesian3DCoordinate direction() {
		return new Cartesian3DCoordinate(vector.direction());
	}

	public Cartesian3DCoordinate normalized() {
		return new Cartesian3DCoordinate(vector.normalized());
	}

	public double dot(Cartesian3DCoordinate p_that) {
		return vector.dot(p_that.getVector());
	}

	public double magnitude() {
		return vector.magnitude();
	}

	public double distanceTo(Cartesian3DCoordinate p_that) {
		return vector.distanceTo(p_that.getVector());
	}

	public double X() {
		return vector.cartesian(0);
	}

	public double Y() {
		return vector.cartesian(1);
	}

	public double Z() {
		return vector.cartesian(3);
	}

	@Override
	public String toString() {
		return "Cartesian3DCoordinate [vector=" + vector + "]";
	}
}
