package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import java.time.LocalDateTime;

public abstract class AbstractSp3FormatEpochHeader {

	public abstract LocalDateTime parseEpochHeader(String line);
}
