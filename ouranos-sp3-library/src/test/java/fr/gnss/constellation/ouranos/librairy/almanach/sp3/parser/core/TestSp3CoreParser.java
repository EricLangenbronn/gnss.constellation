package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;
import org.junit.jupiter.api.Test;

public class TestSp3CoreParser {

  @Test
  public void testConstructeurEmpty() {
    assertThrows(NullPointerException.class, () -> {
      new Sp3CoreParser(null);
    });
  }

  @Test
  public void testConstructeurWithImplementSp3Type_thenOk() {
    new Sp3CoreParser(Sp3FileType.c);
  }

  @Test
  public void testConstructeurWithUnImplementSp3Type_thenOk() {
    assertThrows(UnsupportedOperationException.class, () -> {
      new Sp3CoreParser(Sp3FileType.b);
    });
  }

}
