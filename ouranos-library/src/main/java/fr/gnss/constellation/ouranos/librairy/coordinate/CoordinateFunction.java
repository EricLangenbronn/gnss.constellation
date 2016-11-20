package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class CoordinateFunction {

	/**
	 * WGS 84 (World Geodetic System 1984 : système géodésique mondial, révision
	 * de 1984) est le système géodésique mondial le plus courant, car il est
	 * utilisé par le système GPS.
	 */
	public static double a = 6378137.0000; // demi grand axe en metre
	public static double b = 6356752.3142; // demi petit axe en metre

	public static double[] degreeToRadian(double degree[]) {
		if (degree.length != 3) {
			throw new IllegalArgumentException("Une position doit posséder trois valeurs");
		}

		double[] radian = new double[3];
		radian[0] = Math.toRadians(degree[0]);
		radian[1] = Math.toRadians(degree[1]);
		radian[2] = Math.toRadians(degree[2]);

		return radian;
	}

	public static double[] radianToDegree(double[] radian) {
		if (radian.length != 3) {
			throw new IllegalArgumentException("Une position doit posséder trois valeurs");
		}

		double[] degree = new double[3];
		degree[0] = Math.toRadians(radian[0]);
		degree[1] = Math.toRadians(radian[1]);
		degree[2] = Math.toRadians(radian[2]);

		return degree;
	}

	/**
	 * Aussi appelé geodetic to ECEF transformation
	 * 
	 * @param cg
	 * @return
	 */
	public static CartesianCoordinate3D geodeticToCartesianWSG84(GeodeticCoordinate cg) {
		Objects.requireNonNull(cg, "GeodeticCoordinate");

		double ee = 1 - (Math.pow((b / a), 2));
		double N = CoordinateFunction.a
				/ Math.sqrt(1.0 - ee * (Math.sin(cg.getLatitude()) * Math.sin(cg.getLatitude())));

		double x = (N + cg.getAltitude()) * Math.cos(cg.getLatitude()) * Math.cos(cg.getLongitude());
		double y = (N + cg.getAltitude()) * Math.cos(cg.getLatitude()) * Math.sin(cg.getLongitude());
		double z = ((N * (1 - ee)) + cg.getAltitude()) * Math.sin(cg.getLatitude());

		CartesianCoordinate3D cc = new CartesianCoordinate3D(x, y, z);

		return cc;
	}

	/**
	 * To transform from ECEF coordinates to the local coordinates we need a
	 * local reference point, typically this might be the location of a radar.
	 * If a radar is located at {X_r, Y_r, Z_r} and an aircraft at {X_p, Y_p,
	 * Z_p} then the vector pointing from the radar to the aircraft in the ENU
	 * frame is
	 * 
	 * /!\ Attention Note: phi is the geodetic latitude. Note: Unambiguous
	 * determination of phi and lambda requires knowledge of which quadrant the
	 * coordinates lie in.
	 * 
	 * @param phi
	 *            (en radian)
	 * @param lambda
	 *            (en radian)
	 */
	public static CartesianCoordinate3D transformECEFtoENU(GeodeticCoordinate rotation, CartesianCoordinate3D vector) {
		Objects.requireNonNull(vector, "CartesianCoordinate3D");

		double u = vector.X();
		double v = vector.Y();
		double w = vector.Z();
		// East
		double E1 = -(Math.sin(rotation.getLongitude())) * u;
		double E2 = Math.cos(rotation.getLongitude()) * v;
		double E4 = E1 + E2;

		// North
		double N1 = (-(Math.sin(rotation.getLatitude())) * Math.cos(rotation.getLongitude())) * u;
		double N2 = (-(Math.sin(rotation.getLatitude())) * Math.sin(rotation.getLongitude())) * v;
		double N3 = Math.cos(rotation.getLatitude()) * w;
		double N4 = N1 + N2 + N3;

		// Up
		double U1 = (Math.cos(rotation.getLatitude()) * Math.cos(rotation.getLongitude())) * u;
		double U2 = (Math.cos(rotation.getLatitude()) * Math.sin(rotation.getLongitude())) * v;
		double U3 = Math.sin(rotation.getLatitude()) * w;
		double U4 = U1 + U2 + U3;

		CartesianCoordinate3D res = new CartesianCoordinate3D(E4, N4, U4);

		return res;

	}
}
