package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticTransformation;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/moduleTest/ouranos-services-test.xml" })
public class ITTraitementPosition {

	@Autowired
	private IComputationService computationService;

	@Test
	public void testProcessElevationAzimut() {
		GeodeticCoordinate stationGeo = new GeodeticCoordinate(Math.toRadians(38.889139), Math.toRadians(-77.049),
				130.049);

		CartesianCoordinate3D stationCar = new CartesianCoordinate3D(GeodeticTransformation.geodeticToCartesianWSG84(
				stationGeo.getLatitude(), stationGeo.getLongitude(), stationGeo.getAltitude()));
		CartesianCoordinate3D satelite = new CartesianCoordinate3D(-12110.343226, -13482.507392, -19488.380856);

		double[] angles = GeodeticTransformation.processElevationAzimuth(stationGeo, satelite);
		assertNotNull(angles);

		System.out.println("angle : " + angles);

	}

	/*
	 * @Test public void testProcess() throws Exception { GeodeticCoordinate
	 * gStation = new GeodeticCoordinate(Math.toRadians(38.889139),
	 * Math.toRadians(-77.049), Math.toRadians(130.049)); String sp3FileName =
	 * getClass().getResource("/Sp3File/igs17720.sp3").getFile(); Sp3FileReader
	 * fileReader = new Sp3FileReader(sp3FileName); assertNotNull(fileReader);
	 * 
	 * while (true) { try { ExecutionService exec = new ExecutionService();
	 * assertNotNull(exec);
	 * 
	 * List<Entry<LocalDateTime, List<Satelite>>> map =
	 * exec.getSateliteVisble(); assertNotNull(map);
	 * 
	 * exec.afficheSateliteVisible(map); } catch (BusinessException e) {
	 * System.out.println("end of file"); break; } } }
	 */

	public void setExecutionService(IComputationService executionDao) {
		this.computationService = executionDao;
	}
}
