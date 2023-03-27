package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sp3Const {

  public static LocalDate FIRST_EPOCH_RECORD;

  static {
    FIRST_EPOCH_RECORD = LocalDate.parse("1980-01-06", DateTimeFormatter.ISO_DATE);
  }
}
