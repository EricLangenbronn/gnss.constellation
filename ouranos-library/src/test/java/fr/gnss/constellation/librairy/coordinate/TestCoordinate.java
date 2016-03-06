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
	public void testRransformECEFtoENUStrasbourg() throws Exception {

		GeodeticCoordinate geodeticStation = new GeodeticCoordinate(Math.toRadians(48.5839200), Math.toRadians(7.7455300), 120.0);

		CartesianCoordinate3D cartesianSatelite = new CartesianCoordinate3D(100, 100, 100);
		CartesianCoordinate3D cartesianStation0 = CoordinateFunction.geodeticToCartesianWSG84(geodeticStation);

		CartesianCoordinate3D cartesiantStationSatelite = CartesianCoordinate3D.minus(cartesianSatelite,
				cartesianStation0);

		CartesianCoordinate3D resEver = CoordinateFunction.transformECEFtoENU(geodeticStation,
				cartesiantStationSatelite);
		
		assertEquals(85.6103, resEver.X(), 0.0001);
		assertEquals(21203.716211482417, resEver.Y(), 0.0001);
		assertEquals(-6366089.888330414, resEver.Z(), 0.0001);
		
		// x = 85.6997, y = 2.1255e+04, z = -6.3663e+06

		// MatLab
		// wgs84 = wgs84Ellipsoid('meters');
		// [x ,y z] =
		// ecef2enu(100,100,100,0.8377580409572781,0.13439035240356337,120.0,
		// wgs84, 'radian')
		// [x,y,z] = geodetic2ecef(wgs84,48.0,7.7,120)

	}
}
