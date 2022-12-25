package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestSp3FormatEpochHeaderTypeA {

	@Test
	public void whenParsingNullLine_thenException() {
		Sp3FormatEpochHeaderTypeC sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatEpochHeaderTypeC.parseEpochHeader(null));
	}

	@Test
	public void whenParsingEmptyLine_thenException() {
		Sp3FormatEpochHeaderTypeC sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatEpochHeaderTypeC.parseEpochHeader(""));
	}

	@Test
	public void whenParsingNotEpochLine_thenException() {
		Sp3FormatEpochHeaderTypeC sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		assertThrows(RuntimeException.class, () -> sp3FormatEpochHeaderTypeC.parseEpochHeader("P  1   3599.542036 -25408.517095   6932.849102    355.220265"));
	}

	@Test
	public void whenParsingEpochLine_thenCorrectLocalDateTime() {
		Sp3FormatEpochHeaderTypeC sp3FormatEpochHeaderTypeC = new Sp3FormatEpochHeaderTypeC();

		LocalDateTime epoch = sp3FormatEpochHeaderTypeC.parseEpochHeader("*  2004  7 25  0  0  0.00000000");

		assertEquals(LocalDateTime.of(2004, 7, 25, 0, 0), epoch);
	}
}
