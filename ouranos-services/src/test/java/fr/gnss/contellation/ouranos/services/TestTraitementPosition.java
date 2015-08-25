package fr.gnss.contellation.ouranos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.services.ExecutionService;

public class TestTraitementPosition {

	@Test
	public void testProcessElevationAzimut() {
		GeodeticCoordinate stationGeo = new GeodeticCoordinate(Math.toRadians(38.889139), Math.toRadians(-77.049),
				Math.toRadians(130.049));

		CartesianCoordinate3D stationCar = new CartesianCoordinate3D(
				CoordinateFunction.geodeticToCartesian(stationGeo));
		CartesianCoordinate3D satelite = new CartesianCoordinate3D(-12110.343226, -13482.507392, -19488.380856);

		ExecutionService exec = new ExecutionService();
		assertNotNull(exec);

		SphericalCoordinate sphere = exec.processSphericalCoordinate(stationGeo, stationCar, satelite);
		assertNotNull(exec);

		System.out.println("angle : " + sphere);

	}

	/*
	@Test
	public void testProcess() throws Exception {
		GeodeticCoordinate gStation = new GeodeticCoordinate(Math.toRadians(38.889139), Math.toRadians(-77.049),
				Math.toRadians(130.049));
		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3FileReader fileReader = new Sp3FileReader(sp3FileName);
		assertNotNull(fileReader);

		while (true) {
			try {
				ExecutionService exec = new ExecutionService();
				assertNotNull(exec);

				List<Entry<LocalDateTime, List<Satelite>>> map = exec.getSateliteVisble();
				assertNotNull(map);

				exec.afficheSateliteVisible(map);
			} catch (BusinessException e) {
				System.out.println("end of file");
				break;
			}
		}
	}
	*/
}
