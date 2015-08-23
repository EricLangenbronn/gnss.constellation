package fr.gnss.contellation.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.service.ExecutionService;

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

		ExecutionService exec = new ExecutionService();
		SphericalCoordinate sphere = exec.processSphericalCoordinate(stationGeo,
				stationCar, satelite);

		System.out.println("angle : " + sphere);

	}

	@Test
	public void testProcess() throws Exception {
		GeodeticCoordinate gStation = new GeodeticCoordinate(
				Math.toRadians(38.889139), Math.toRadians(-77.049),
				Math.toRadians(130.049));
		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3")
				.getFile();
		Sp3FileReader fileReader = new Sp3FileReader(sp3FileName);

		while (true) {
			try {
				ExecutionService exec = new ExecutionService();
				List<Entry<LocalDateTime, List<Satelite>>> map = exec
						.getSateliteVisbleAll(fileReader, gStation);
				exec.afficheSateliteVisible(map);
			} catch (BusinessException e) {
				System.out.println("end of file");
				break;
			}
		}
	}
}
