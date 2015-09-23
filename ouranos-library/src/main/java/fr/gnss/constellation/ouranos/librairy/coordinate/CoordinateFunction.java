package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

import org.ejml.simple.SimpleMatrix;

public class CoordinateFunction {

	/**
	 * WGS 84 (World Geodetic System 1984 : système géodésique mondial, révision
	 * de 1984) est le système géodésique mondial le plus courant, car il est
	 * utilisé par le système GPS.
	 */
	public static double a = 6378137.0; // demi grand axe en metre
	public static double f = 0.00335281066474748071984552861852; // degré
																	// d'applatissement

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
	public static CartesianCoordinate3D geodeticToCartesian(GeodeticCoordinate cg) {
		Objects.requireNonNull(cg, "GeodeticCoordinate");

		double e = (2.0 - CoordinateFunction.f) * CoordinateFunction.f;
		double N = CoordinateFunction.a / Math.sqrt(1.0 - e * Math.sin(cg.getLatitude()) * Math.sin(cg.getLatitude()));

		double x = (N + cg.getAltitude()) * Math.cos(cg.getLatitude()) * Math.cos(cg.getLongitude());
		double y = (N + cg.getAltitude()) * Math.cos(cg.getLatitude()) * Math.sin(cg.getLongitude());
		double z = ((N * (1 - Math.pow(e, 2))) + cg.getAltitude()) * Math.sin(cg.getLatitude());

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
	public static CartesianCoordinate3D transformECEFtoENU(double phi, double lambda, CartesianCoordinate3D satelite,
			CartesianCoordinate3D station) {
		Objects.requireNonNull(satelite, "CartesianCoordinate3D");
		Objects.requireNonNull(station, "CartesianCoordinate3D");

		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite.normalized(),
				station.normalized());

		double[][] matriceData2 = new double[1][3];
		matriceData2[0][0] = stationSatelite.X();
		matriceData2[0][1] = stationSatelite.Y();
		matriceData2[0][2] = stationSatelite.Z();

		SimpleMatrix b = new SimpleMatrix(matriceData2);

		double[][] matriceData = new double[3][3];
		matriceData[0][0] = -Math.sin(lambda);
		matriceData[0][1] = Math.cos(lambda);
		matriceData[0][2] = 0;

		matriceData[1][0] = -Math.sin(phi) * Math.cos(lambda);
		matriceData[1][1] = -Math.sin(phi) * Math.sin(lambda);
		matriceData[1][2] = Math.cos(phi);

		matriceData[2][0] = Math.cos(phi) * Math.cos(lambda);
		matriceData[2][1] = Math.cos(phi) * Math.sin(lambda);
		matriceData[2][2] = Math.sin(phi);
		SimpleMatrix a = new SimpleMatrix(matriceData);

		SimpleMatrix c = b.mult(a);

		CartesianCoordinate3D res = new CartesianCoordinate3D(c.get(0), c.get(1), c.get(2));

		return res;

	}

	public static double getAzimut(double x, double y) {
		return Math.atan2(y, x);
	}
}
