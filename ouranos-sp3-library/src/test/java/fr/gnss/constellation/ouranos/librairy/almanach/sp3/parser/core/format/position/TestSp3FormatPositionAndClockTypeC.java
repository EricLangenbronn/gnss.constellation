package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class TestSp3FormatPositionAndClockTypeC {

	@Test
	public void whenParsingNullLine_thenException() {
		Sp3FormatPositionAndClockTypeC sp3FormatPositionAndClockTypeC = new Sp3FormatPositionAndClockTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeC.parsePositionAndClock(null));
	}

	@Test
	public void whenParsingEmptyLine_thenException() {
		Sp3FormatPositionAndClockTypeC sp3FormatPositionAndClockTypeC = new Sp3FormatPositionAndClockTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeC.parsePositionAndClock(""));
	}

	@Test
	public void whenParsingNotEpochLine_thenException() {
		Sp3FormatPositionAndClockTypeC sp3FormatPositionAndClockTypeC = new Sp3FormatPositionAndClockTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeC.parsePositionAndClock("*  2013 12 22  0  0  0.00000000"));
	}

	@Test
	public void whenParsingEpochLine_thenCorrectLocalDateTime() {
		Sp3FormatPositionAndClockTypeC sp3FormatPositionAndClockTypeC = new Sp3FormatPositionAndClockTypeC();

		SatellitePosition<CartesianCoordinate3D> position = sp3FormatPositionAndClockTypeC.parsePositionAndClock("PG01 -17978.872222  -5143.579128  18870.417705     95.049237  6  8  8 148       ");

		assertNotNull(position);
		assertEquals("G01", position.getVehicleId());
		assertNotNull(position.getPosition());
		assertEquals(3, position.getPosition().getDimensions());
		assertNotNull(position.getPosition().getPosition());
		assertEquals(-17978872.222, position.getPosition().getPosition()[0], 0.00000001d);
		assertEquals(-5143579.128, position.getPosition().getPosition()[1], 0.00000001d);
		assertEquals(18870417.705, position.getPosition().getPosition()[2], 0.00000001d);
	}

}
