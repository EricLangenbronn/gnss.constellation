package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.epoch;

import java.time.LocalDateTime;

public sealed interface ISp3FormatEpochHeader
    permits Sp3FormatEpochHeaderTypeA, Sp3FormatEpochHeaderTypeC {

  LocalDateTime parseEpochHeader(String line);
}
