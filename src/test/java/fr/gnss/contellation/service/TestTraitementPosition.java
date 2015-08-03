package fr.gnss.contellation.service;

import org.junit.Test;

import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.service.TraitementPositions;

public class TestTraitementPosition {

	@Test
	public void testProcessElevationAzimut() {
		GeodeticCoordinate stationGeo = new GeodeticCoordinate(
				Math.toRadians(38.889139), Math.toRadians(-77.049),
				Math.toRadians(130.049));

		CartesianCoordinate3D stationCar = new CartesianCoordinate3D(
				CoordinateFunction.geodeticToCartesian(stationGeo));
		CartesianCoordinate3D satelite = new CartesianCoordinate3D(
				-12110.343226, -13482.507392, -19488.380856);

		double[] angle = TraitementPositions.processElevationAzimut(stationGeo,
				stationCar, satelite);

		System.out.println("angle : " + angle[0] + ", " + angle[1] + ", "
				+ angle[2]);

	}

	@Test
	public void testProcess() throws Exception {
		GeodeticCoordinate gStation = new GeodeticCoordinate(
				Math.toRadians(38.889139), Math.toRadians(-77.049),
				Math.toRadians(130.049));
		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3")
				.getFile();

		TraitementPositions.getSateliteVisble(sp3FileName, gStation);
	}
}
