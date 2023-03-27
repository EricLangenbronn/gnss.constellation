package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;


public class CartesianCoordinate3D implements ICoordinate {

  private final Abscisse abscisse;
  private final Ordonnee ordonnee;
  private final Cote cote;

  /**
   * Initializes a newly created CartesianCoordinate3D object [0,0,0].
   */
  public CartesianCoordinate3D() {
    this.abscisse = new Abscisse();
    this.ordonnee = new Ordonnee();
    this.cote = new Cote();
  }

  /**
   * Constructs a newly allocated CartesianCoordinate3D object that represents
   * the specified double values.
   *
   * @param x - abscissa
   * @param y - ordinate
   * @param z - height
   */
  public CartesianCoordinate3D(double x, double y, double z) {
    this.abscisse = new Abscisse(x);
    this.ordonnee = new Ordonnee(y);
    this.cote = new Cote(z);
  }

  /**
   * Constructs a newly allocated CartesianCoordinate3D object that represents
   * the specified array of double.
   *
   * @param p - coordinates list
   */
  public CartesianCoordinate3D(double... p) {
    if (p == null || p.length != 3) {
      throw new IllegalArgumentException(
          "Une position cartesienne doit posséder trois valeurs (x, y, y)");
    }

    this.abscisse = new Abscisse(p[0]);
    this.ordonnee = new Ordonnee(p[1]);
    this.cote = new Cote(p[2]);
  }

  /**
   * Initializes a newly created CartesianCoordinate3D object so that it
   * represents the same position of CartesianCoordinate3D as the argument; in
   * other words, the newly created CartesianCoordinate3D is a copy of the
   * argument CartesianCoordinate3D.
   *
   * @param p - A CartesianCoordinate3D
   */
  public CartesianCoordinate3D(CartesianCoordinate3D p) {
    Objects.requireNonNull(p, "CartesianCoordinate3D");

    this.abscisse = new Abscisse(p.getPosition()[0]);
    this.ordonnee = new Ordonnee(p.getPosition()[1]);
    this.cote = new Cote(p.getPosition()[2]);

  }

  /**
   * Subtract a CartesianCoordinate3D from the other. (v1 - v2)
   *
   * @param v1 -
   * @param v2 - CartesianCoordinate3D to subtract
   * @return the specified new CartesianCoordinate3D
   */
  public static CartesianCoordinate3D minus(CartesianCoordinate3D v1, CartesianCoordinate3D v2) {
    Objects.requireNonNull(v1, "CartesianCoordinate3D");
    Objects.requireNonNull(v2, "CartesianCoordinate3D");

    return new CartesianCoordinate3D(
        v1.getAbscisse() - v2.getAbscisse()
        , v1.getOrdonnee() - v2.getOrdonnee()
        , v1.getCote() - v2.getCote()
    );
  }

  /**
   * Get the norm of the instance.
   *
   * @return the norm of the instance.
   */
  public double magnitude() {
    return Math.sqrt(Math.pow(this.abscisse.getValue(), 2) + Math.pow(this.ordonnee.getValue(), 2) + Math.pow(this.cote.getValue(), 2));
  }

  /**
   * Get a normalized vector aligned with the instance.
   *
   * @return a new normalized CartesianCoordinate3D
   */
  public CartesianCoordinate3D normalized() {
    double norme = this.magnitude();

    return new CartesianCoordinate3D(this.abscisse.getValue() / norme, this.ordonnee.getValue() / norme, this.cote.getValue() / norme);
  }

  /**
   * Compute the dot-product of the instance with an other
   * CartesianCoordinate3D.
   *
   * @param c - A CartesianCoordinate3D
   * @return the dot product
   */
  public double dotProduct(CartesianCoordinate3D c) {
    Objects.requireNonNull(c, "CartesianCoordinate3D");
    return this.abscisse.getValue() * c.getAbscisse() + this.ordonnee.getValue() * c.getOrdonnee() + this.cote.getValue() * c.getCote();
  }

  /**
   * Compute the angular separation of the instance with an other
   * CartesianCoordinate3D.
   *
   * @param c - A CartesianCoordinate3D
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
  public double getAbscisse() {
    return this.abscisse.getValue();
  }

  /**
   * Get the ordinate of the vector.
   *
   * @return the ordinate of the vector
   */
  public double getOrdonnee() { // profondeur
    return this.ordonnee.getValue();
  }

  /**
   * Get the height of the vector.
   *
   * @return the height of the vector
   */
  public double getCote() {
    return this.cote.getValue();
  }

  @Override
  public double[] getPosition() {

    double[] position = new double[3];
    position[0] = this.abscisse.getValue();
    position[1] = this.ordonnee.getValue();
    position[2] = this.cote.getValue();

    return position;
  }

  @Override
  public int getDimensions() {
    return 3;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof CartesianCoordinate3D anotherPosition) {
      return (this.getAbscisse() == anotherPosition.getAbscisse()) && (this.getOrdonnee() == anotherPosition.getOrdonnee())
          && (this.getCote() == anotherPosition.getCote());
    }

    return false;
  }

  @Override
  public String toString() {
    return "CoordinateCartesian [abscisse=" + this.abscisse.getValue() + ", ordonnee=" + this.ordonnee.getValue() + ", cote="
        + this.cote.getValue() + "]";
  }

  public static class Abscisse {

    private final Double abscisse;

    public Abscisse() {
      this(0.0);
    }

    public Abscisse(Double x) {

      if (x == null) {
        throw new IllegalArgumentException("La valeur de X (abscisse) ne peut être null");
      }

      this.abscisse = x;
    }

    public Double getValue() {
      return abscisse;
    }
  }

  public static class Ordonnee {

    private final Double ordonnee;

    public Ordonnee() {
      this(0.0);
    }

    public Ordonnee(Double y) {

      if (y == null) {
        throw new IllegalArgumentException("La valeur de Y (ordonnée) ne peut être null");
      }

      this.ordonnee = y;
    }

    public Double getValue() {
      return ordonnee;
    }
  }

  public static class Cote {

    private final Double cote;

    public Cote() {
      this(0.0);
    }

    public Cote(Double z) {

      if (z == null) {
        throw new IllegalArgumentException("La valeur de Z (cote) ne peut être null");
      }

      this.cote = z;
    }

    public Double getValue() {
      return cote;
    }
  }
}
