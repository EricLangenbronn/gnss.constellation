package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestSp3FormatEpochHeaderTypeC {

	@Test
	public void whenParsingNullLine_thenException() {
		Sp3FormatEpochHeaderTypeC Sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class, () -> Sp3FormatEpochHeaderTypeC.parseEpochHeader(null));
	}

	@Test
	public void whenParsingEmptyLine_thenException() {
		Sp3FormatEpochHeaderTypeC Sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class, () -> Sp3FormatEpochHeaderTypeC.parseEpochHeader(""));
	}

	@Test
	public void whenParsingNotEpochLine_thenException() {
		Sp3FormatEpochHeaderTypeC Sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class,
				() -> Sp3FormatEpochHeaderTypeC.parseEpochHeader("PG01  15808.963455   1383.239271  21178.271065   -189.521559  7  5  6  95       "));
	}

	@Test
	public void whenParsingEpochLine_thenCorrectLocalDateTime() {
		Sp3FormatEpochHeaderTypeC Sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		LocalDateTime epoch = Sp3FormatEpochHeaderTypeC.parseEpochHeader("*  2019  3 28  0  0  0.00000000");

		assertEquals(LocalDateTime.of(2019, 3, 28, 0, 0), epoch);
	}
}
