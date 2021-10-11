package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.header.Sp3Header;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class TestSp3Reader {

	@Test
	public void testWhenFileExist_thenObjectOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));

		assertNotNull(sp3Reader);
	}

	@Test
	public void testWhenReadHeader_thenSatIdsSizeOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		Sp3Header sp3Header = sp3Reader.getSp3Header();
		assertEquals(32, sp3Header.getSatId().size());
	}

	@Test
	public void testWhenReadCore_thenSatPositionOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		LocalDateTime startClock = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endClock = LocalDateTime.parse("2013-12-22T00:15", DateTimeFormatter.ISO_DATE_TIME);

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(1, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -1.7978872222000003E7, -5143579.1280000005, 1.8870417705E7 }, satelliteG01.getPosition().getPosition());
	}

	@Test
	public void testWhenReadCoreWithSkipPeriod_thenSatPositionOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		LocalDateTime startClock = LocalDateTime.parse("2013-12-22T00:15", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endClock = LocalDateTime.parse("2013-12-22T00:30", DateTimeFormatter.ISO_DATE_TIME);

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(1, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -1.8928577316E7, -6990226.154, 1.7294914165E7 }, satelliteG01.getPosition().getPosition());
	}

	@Test
	public void testWhenReadCoreSuccessivlyPeriod_thenSatPositionOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		LocalDateTime startClock = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endClock = LocalDateTime.parse("2013-12-22T00:15", DateTimeFormatter.ISO_DATE_TIME);

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(1, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -1.7978872222000003E7, -5143579.1280000005, 1.8870417705E7 }, satelliteG01.getPosition().getPosition());

		startClock = LocalDateTime.parse("2013-12-22T00:15", DateTimeFormatter.ISO_DATE_TIME);
		endClock = LocalDateTime.parse("2013-12-22T00:30", DateTimeFormatter.ISO_DATE_TIME);

		positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(1, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -1.8928577316E7, -6990226.154, 1.7294914165E7 }, satelliteG01.getPosition().getPosition());
	}

	@Test
	public void testWhenReadCoreSuccessivlyWithLargePeriodANdLargeInterval_thenSatPositionOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		LocalDateTime startClock = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endClock = LocalDateTime.parse("2013-12-22T00:45", DateTimeFormatter.ISO_DATE_TIME);

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(3, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		SatellitePosition<CartesianCoordinate3D> satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -1.7978872222000003E7, -5143579.1280000005, 1.8870417705E7 }, satelliteG01.getPosition().getPosition());

		startClock = LocalDateTime.parse("2013-12-22T01:00", DateTimeFormatter.ISO_DATE_TIME);
		endClock = LocalDateTime.parse("2013-12-22T01:45", DateTimeFormatter.ISO_DATE_TIME);

		positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(3, positionsAndClocksRecords.size());
		assertEquals(startClock, positionsAndClocksRecords.get(0).getEpochHeaderRecord());
		assertEquals(32, positionsAndClocksRecords.get(0).getSatellites().size());
		satelliteG01 = positionsAndClocksRecords.get(0).getSatellites().get("G01");
		assertArrayEquals(new double[] { -2.1520481046E7, -1.119001887E7, 1.0919909334E7 }, satelliteG01.getPosition().getPosition());
	}

	@Test
	public void testWhenReadCoreAllPeriod_thenSatPositionOk() throws Exception {

		String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
		Sp3Reader sp3Reader = new Sp3Reader(new Sp3File(sp3FileName));
		assertNotNull(sp3Reader);

		LocalDateTime startClock = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endClock = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> positionsAndClocksRecords = sp3Reader.getPositionsAndClocksRecords(startClock, endClock);
		assertNotNull(positionsAndClocksRecords);
		assertFalse(positionsAndClocksRecords.isEmpty());
		assertEquals(96, positionsAndClocksRecords.size());
		for (SatelliteTimeCoordinate<CartesianCoordinate3D> positionsAndClockRecord : positionsAndClocksRecords) {
			assertEquals(32, positionsAndClockRecord.getSatellites().size());
		}

	}
}
