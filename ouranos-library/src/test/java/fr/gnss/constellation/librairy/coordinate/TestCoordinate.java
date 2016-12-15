package fr.gnss.constellation.librairy.coordinate;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class TestCoordinate {

	// http://www.apsalin.com/online.aspx

	@Test
	public void testGeodeticToCartesianStrasbourg() throws Exception {
		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300),
				120.0);

		CartesianCoordinate3D cartesianStation = CoordinateFunction.geodeticToCartesianWSG84(station);

		assertEquals(4188756.20483369, cartesianStation.X(), 0.0001);
		assertEquals(569731.86031346, cartesianStation.Y(), 0.0001);
		assertEquals(4760166.05125538, cartesianStation.Z(), 0.0001);

	}

	@Test
	public void testGeodeticToCartesianParis() throws Exception {
		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.8534100), Math.toRadians(2.3488000),
				120.0);

		CartesianCoordinate3D cartesianStation = CoordinateFunction.geodeticToCartesianWSG84(station);

		assertEquals(4201270.83938198, cartesianStation.X(), 0.0001);
		assertEquals(172324.672938237, cartesianStation.Y(), 0.0001);
		assertEquals(4779938.29153465, cartesianStation.Z(), 0.0001);

	}

	@Test
	public void testTransformECEFtoENUStrasbourg() throws Exception {

		CartesianCoordinate3D cartesiantStationSatelite = new CartesianCoordinate3D(4201270.83938198, 172324.672938237,
				4779938.29153465);
		GeodeticCoordinate geodeticStation = new GeodeticCoordinate(Math.toRadians(48.5839200),
				Math.toRadians(7.7455300), 120.0);

		CartesianCoordinate3D resEver = CoordinateFunction.transformECEFtoENU(geodeticStation,
				cartesiantStationSatelite);

		assertEquals(22724.3945970642, resEver.X(), 0.0001);
		assertEquals(-395468.0517734572, resEver.Y(), 0.0001);
		assertEquals(6353839.0876335958, resEver.Z(), 0.0001);
	}

	@Test
	public void testCalcElevationAzimuth() throws Exception {

		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300),
				120.0);
		CartesianCoordinate3D cartesianStation = CoordinateFunction.geodeticToCartesianWSG84(station);
		// [5441.177312, -25836.148765, -2502.546747];
		CartesianCoordinate3D satelite = new CartesianCoordinate3D(5441.177312, -25836.148765, -2502.546747);

		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite, cartesianStation);
		CartesianCoordinate3D stationSateliteNorm = stationSatelite.normalized();
		CartesianCoordinate3D enuStationSatelite = CoordinateFunction.transformECEFtoENU(station, stationSateliteNorm);

		double[] angles = new double[3];
		double normeStaSat = enuStationSatelite.magnitude();

		angles[0] = normeStaSat;
		// Angle d'élévation
		angles[1] = Math.asin(enuStationSatelite.Z() / normeStaSat);
		System.out.println("elevation : " + angles[1]);

		// Angle azimute
		angles[2] = Math.atan2(enuStationSatelite.X() / normeStaSat, enuStationSatelite.Y() / normeStaSat);
		System.out.println("azimute : " + angles[2]);

	}

	@Test
	public void testCalcElevationAzimuth32Sat() throws Exception {

		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300),
				120.0);
		CartesianCoordinate3D cartesianStation = CoordinateFunction.geodeticToCartesianWSG84(station);
		// [5441.177312, -25836.148765, -2502.546747];

		double[][] satelites = { { 5441.177312, -25836.148765, -2502.546747 },
				{ 22512.943525, 11279.067086, -8259.910943 }, { 5441.177312, -25836.148765, -2502.546747 },
				{ 11861.950014, -18363.301159, 14820.905968 }, { -25122.480824, 3926.313296, -8210.207167 },
				{ 6042.443322, 19175.056679, -17577.164038 }, { 8102.426483, 21355.293956, 13649.257310 },
				{ -15484.856046, -1272.401488, -21124.503175 }, { -22023.939536, -2340.406066, 14708.624099 },
				{ -5834.483486, 17801.926514, -19142.787691 }, { -11961.479077, 9253.150793, 21801.021030 },
				{ 3980.887554, -18754.497376, -18332.154070 }, { -8043.411536, -19787.598256, 15771.004072 },
				{ 14549.390698, 2931.243237, -21984.618163 }, { 25331.331671, -3110.771586, 7643.742068 },
				{ 16650.263990, -5122.390995, 20135.482150 }, { -15487.867326, 15289.081436, 14879.416630 },
				{ 19276.595866, 18262.792384, 2766.423071 }, { 6879.100063, -25467.612385, 3484.331528 },
				{ -7928.323062, -19669.879168, -16062.447540 }, { 16082.181477, 8691.310249, 19387.192311 },
				{ 22512.943525, 11279.067086, -8259.910943 }, { 863.766942, -26303.066779, 3100.316546 },
				{ -23367.795578, 11998.356766, 4508.798163 }, { 24393.560388, -9228.032801, -4669.161227 },
				{ -8172.795221, 23671.887428, 7566.810408 }, { -15018.519878, -8968.731026, 20612.233740 },
				{ -23134.797276, -10461.141878, -8121.468682 }, { -10317.732443, 21291.103334, 12229.412137 },
				{ 16323.709402, 19180.877464, -8837.094399 }, { -14704.450896, -8506.194718, -20682.914113 },

		};

		for (int i = 0; i < satelites.length; ++i) {
			System.out.println(i + "  #################################");
			CartesianCoordinate3D satelite = new CartesianCoordinate3D(satelites[i]);
			CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite, cartesianStation);
			CartesianCoordinate3D stationSateliteNorm = stationSatelite.normalized();
			CartesianCoordinate3D enuStationSatelite = CoordinateFunction.transformECEFtoENU(station,
					stationSateliteNorm);

			double[] angles = new double[3];
			double normeStaSat = enuStationSatelite.magnitude();

			angles[0] = normeStaSat;
			// Angle d'élévation
			angles[1] = Math.asin(enuStationSatelite.Z() / normeStaSat);
			System.out.println("elevation : " + angles[1]);

			// Angle azimute
			angles[2] = Math.atan2(enuStationSatelite.X() / normeStaSat, enuStationSatelite.Y() / normeStaSat);
			System.out.println("azimute : " + angles[2]);
		}

	}
}
