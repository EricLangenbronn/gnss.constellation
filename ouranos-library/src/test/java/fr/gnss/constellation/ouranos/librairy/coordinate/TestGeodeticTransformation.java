package fr.gnss.constellation.ouranos.librairy.coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestGeodeticTransformation {

	@Test
	public void testGeodeticToCartesianStrasbourg() throws Exception {

		double[] cartesianPosition = GeodeticTransformation.geodeticToCartesianWSG84(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);

		assertEquals(4188756.20483369, cartesianPosition[0], 0.0001);
		assertEquals(569731.86031346, cartesianPosition[1], 0.0001);
		assertEquals(4760166.05125538, cartesianPosition[2], 0.0001);

	}

	@Test
	public void testGeodeticToCartesianParis() throws Exception {

		double[] cartesianPosition = GeodeticTransformation.geodeticToCartesianWSG84(Math.toRadians(48.8534100), Math.toRadians(2.3488000), 120.0);

		assertEquals(4201270.83938198, cartesianPosition[0], 0.0001);
		assertEquals(172324.672938237, cartesianPosition[1], 0.0001);
		assertEquals(4779938.29153465, cartesianPosition[2], 0.0001);

	}

	@Test
	public void testTransformECEFtoENUStrasbourg() throws Exception {

		CartesianCoordinate3D cartesiantStationSatelite = new CartesianCoordinate3D(4201270.83938198, 172324.672938237, 4779938.29153465);

		double[] resEver = GeodeticTransformation.transformECEFtoENU(Math.toRadians(48.5839200), Math.toRadians(7.7455300),
				cartesiantStationSatelite.getVector());

		assertEquals(-395468.0517734572, resEver[0], 0.0001);
		assertEquals(22724.3945970642, resEver[1], 0.0001);
		assertEquals(6353839.0876335958, resEver[2], 0.0001);
	}

	@Test
	public void testCalcElevationAzimuthSat() throws Exception {

		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);
		double[] cartesianStation = GeodeticTransformation.geodeticToCartesianWSG84(station.getLatitude(), station.getLongitude(), station.getAltitude());

		assertEquals(4188756.20483369, cartesianStation[0], 0.0001);
		assertEquals(569731.86031346, cartesianStation[1], 0.0001);
		assertEquals(4760166.05125538, cartesianStation[2], 0.0001);

		double[] positionECEFSat = { 5441177.312, -25836148.765, -2502546.747 };

		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(new CartesianCoordinate3D(positionECEFSat),
				new CartesianCoordinate3D(cartesianStation));

		assertEquals(1252421.1071663070, stationSatelite.X(), 0.0001);
		assertEquals(-26405880.6253134608, stationSatelite.Y(), 0.0001);
		assertEquals(-7262712.7983230464, stationSatelite.Z(), 0.0001);

		double[] enuStationSateliteVector = GeodeticTransformation.transformECEFtoENU(station.getLatitude(), station.getLongitude(),
				stationSatelite.getVector());

		assertEquals(-26333757.6613142528, enuStationSateliteVector[0], 0.0001);
		assertEquals(-3066253.3858688683, enuStationSateliteVector[1], 0.0001);
		assertEquals(-6979784.0244327448, enuStationSateliteVector[2], 0.0001);

		CartesianCoordinate3D enuStationSatelite = new CartesianCoordinate3D(enuStationSateliteVector);

		double normeStaSat = enuStationSatelite.magnitude();

		// Angle d'élévation
		double elevation = Math.asin(enuStationSatelite.Z() / normeStaSat);
		assertEquals(-0.2574305456, elevation, 0.0001);

		// Angle azimute
		double azimute = Math.atan2(enuStationSatelite.X() / normeStaSat, enuStationSatelite.Y() / normeStaSat);
		assertEquals(-1.6867124757, azimute, 0.0001);

	}

	@Test
	public void testCalcElevationAzimuthSatUseLibrairie() throws Exception {

		GeodeticCoordinate station = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);
		CartesianCoordinate3D satelite = new CartesianCoordinate3D(5441177.312, -25836148.765, -2502546.747);

		double[] angles = GeodeticTransformation.processElevationAzimuth(station, satelite);

		// Angle d'élévation
		assertEquals(-0.2574305456, angles[1], 0.0001);
		// Angle azimute
		assertEquals(-1.6867124757, angles[2], 0.0001);
	}
}
