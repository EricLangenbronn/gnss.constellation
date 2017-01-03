package fr.gnss.constellation.ouranos.librairy.coordinate;

import java.util.Objects;

public class GeodeticTransformation {

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
	 * Convert geodetic to geocentric (ECEF) coordinates.
	 * 
	 * @param phi
	 *            - latitude in radian
	 * @param lambda
	 *            - longitude in radian
	 * @param heights
	 *            - altitude in radian
	 * @return A Cartesian coordinate, that is a 3D vector
	 */
	public static double[] geodeticToCartesianWSG84(double phi, double lambda, double heights) {
		Objects.requireNonNull(phi, "phi");
		Objects.requireNonNull(lambda, "lambda");
		Objects.requireNonNull(heights, "heights");

		double ee = 1 - (Math.pow((b / a), 2));
		double N = GeodeticTransformation.a / Math.sqrt(1.0 - ee * (Math.sin(phi) * Math.sin(phi)));

		double x = (N + heights) * Math.cos(phi) * Math.cos(lambda);
		double y = (N + heights) * Math.cos(phi) * Math.sin(lambda);
		double z = ((N * (1 - ee)) + heights) * Math.sin(phi);

		double[] cc = { x, y, z };

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
	 *            - latitude (en radian)
	 * @param lambda
	 *            - longitude (en radian)
	 * @param vector
	 *            - vector 3D to rotate
	 * @return A 3D vector North, East, Up
	 */
	public static double[] transformECEFtoENU(double phi, double lambda, double[] vector) {
		Objects.requireNonNull(vector, "CartesianCoordinate3D");

		if (vector.length != 3) {
			throw new IllegalArgumentException("Vector size 3");
		}

		double u = vector[0];
		double v = vector[1];
		double w = vector[2];
		// East
		double E1 = -(Math.sin(lambda)) * u;
		double E2 = Math.cos(lambda) * v;
		double E4 = E1 + E2;

		// North
		double N1 = (-(Math.cos(lambda)) * Math.sin(phi)) * u;
		double N2 = (-(Math.sin(lambda)) * Math.sin(phi)) * v;
		double N3 = Math.cos(phi) * w;
		double N4 = N1 + N2 + N3;

		// Up
		double U1 = (Math.cos(lambda) * Math.cos(phi)) * u;
		double U2 = (Math.sin(lambda) * Math.cos(phi)) * v;
		double U3 = Math.sin(phi) * w;
		double U4 = U1 + U2 + U3;

		double[] res = { E4, N4, U4 };

		return res;

	}

	/**
	 * 
	 * @param gStation
	 * @param station
	 * @param satelite
	 * @return norme, élévation, azimut
	 */
	public static double[] processElevationAzimuth(GeodeticCoordinate gStation, CartesianCoordinate3D satelite) {

		double[] stationWSF84 = GeodeticTransformation.geodeticToCartesianWSG84(gStation.getLatitude(),
				gStation.getLongitude(), gStation.getAltitude());
		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite,
				new CartesianCoordinate3D(stationWSF84));
		CartesianCoordinate3D stationSateliteNorm = stationSatelite.normalized();

		double[] enuStationSatelite = GeodeticTransformation.transformECEFtoENU(gStation.getLatitude(),
				gStation.getLongitude(), stationSateliteNorm.getVector());

		double[] angles = new double[3];
		double normeStaSat = Math.sqrt(Math.pow(enuStationSatelite[0], 2) + Math.pow(enuStationSatelite[1], 2)
				+ Math.pow(enuStationSatelite[2], 2));

		angles[0] = normeStaSat;
		// Angle d'élévation
		angles[1] = Math.asin(enuStationSatelite[2] / normeStaSat);

		// Angle azimute
		angles[2] = Math.atan2(enuStationSatelite[0] / normeStaSat, enuStationSatelite[1] / normeStaSat);

		return angles;
	}
}
