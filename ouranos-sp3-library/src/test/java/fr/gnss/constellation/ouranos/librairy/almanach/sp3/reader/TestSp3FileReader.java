package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

public class TestSp3FileReader {

  @Test
  public void testConstructeurEmpty() {
    assertThrows(NullPointerException.class, () -> {
      new Sp3FileReader(null);
    });
  }

  @Test
  public void testConstructeurBadUrl() {
    assertThrows(FileNotFoundException.class, () -> {
      new Sp3FileReader(new Sp3File("/lmolcat.txt"));
    });
  }

  @Test
  public void testWhenFileDoesNotExist_thenException() {
    assertThrows(FileNotFoundException.class, () -> {
      new Sp3FileReader(new Sp3File(""));
    });
  }

  @Test
  public void testWhenFileExist_thenObjectOk() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3FileReader sp3Reader = new Sp3FileReader(new Sp3File(sp3FileName));

    assertNotNull(sp3Reader);
  }

  @Test
  public void testWhenHeaderIsRead_thenIsEndOfHeader() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3FileReader sp3Reader = new Sp3FileReader(new Sp3File(sp3FileName));

    for (int i = 0; i < 18; i++) { // for this specific SP3C file it's 18 header line
      sp3Reader.readLine();
    }

    assertTrue(sp3Reader.isEndOfHeader());
  }

  @Test
  public void testWhenReadHeader_thenGetFirstEpoch() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3FileReader sp3Reader = new Sp3FileReader(new Sp3File(sp3FileName));

    for (int i = 0; i < 18; i++) { // for this specific SP3C file it's 18 header line
      sp3Reader.readLine();
    }

    assertEquals("*  2013 12 22  0  0  0.00000000", sp3Reader.readLine());
  }

  @Test
  public void testWhenReadHeaderWithtoutSkipComment_thenGetComment() throws Exception {

    String sp3FileName = getClass().getResource("/Sp3File/igs17720.sp3").getFile();
    Sp3FileReader sp3Reader = new Sp3FileReader(new Sp3File(sp3FileName), false);

    for (int i = 0; i < 18; i++) { // for this specific SP3C file it's 18 header line
      sp3Reader.readLine();
    }

    assertEquals("/* FINAL ORBIT COMBINATION FROM WEIGHTED AVERAGE OF:        ", sp3Reader.readLine());
  }

}
