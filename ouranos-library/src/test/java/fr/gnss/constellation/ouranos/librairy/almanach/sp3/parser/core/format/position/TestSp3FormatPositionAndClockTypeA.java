package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class TestSp3FormatPositionAndClockTypeA {

	@Test
	public void whenParsingNullLine_thenException() {
		Sp3FormatPositionAndClockTypeA sp3FormatPositionAndClockTypeA = new Sp3FormatPositionAndClockTypeA();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeA.parsePositionAndClock(null));
	}

	@Test
	public void whenParsingEmptyLine_thenException() {
		Sp3FormatPositionAndClockTypeA sp3FormatPositionAndClockTypeA = new Sp3FormatPositionAndClockTypeA();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeA.parsePositionAndClock(""));
	}

	@Test
	public void whenParsingNotEpochLine_thenException() {
		Sp3FormatPositionAndClockTypeA sp3FormatPositionAndClockTypeA = new Sp3FormatPositionAndClockTypeA();

		assertThrows(RuntimeException.class, () -> sp3FormatPositionAndClockTypeA.parsePositionAndClock("*  2004  7 25  0  0  0.00000000"));
	}

	@Test
	public void whenParsingEpochLine_thenCorrectLocalDateTime() {
		Sp3FormatPositionAndClockTypeA sp3FormatPositionAndClockTypeA = new Sp3FormatPositionAndClockTypeA();

		SatellitePosition<CartesianCoordinate3D> position = sp3FormatPositionAndClockTypeA.parsePositionAndClock("P  1   3599.542036 -25408.517095   6932.849102    355.220265");

		assertNotNull(position);
		assertEquals("1", position.getVehicleId());
		assertNotNull(position.getPosition());
		assertEquals(3, position.getPosition().getDimensions());
		assertNotNull(position.getPosition().getPosition());
		assertEquals(3599542.036, position.getPosition().getPosition()[0], 0.00000001d);
		assertEquals(-25408517.095, position.getPosition().getPosition()[1], 0.00000001d);
		assertEquals(6932849.102, position.getPosition().getPosition()[2], 0.00000001d);
	}

}
