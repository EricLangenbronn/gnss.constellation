package fr.gnss.constellation.ouranos.librairy.almanach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import org.junit.jupiter.api.Test;

public class TestEphemerideFileMetaData {
    
  @Test
  public void TestFinalFileNameOk() {
    String fileName = "igs12810.sp3.Z";
    Sp3FileName efmd = new Sp3FileName(fileName);
    assertNotNull(efmd);
  }

  @Test
  public void TestFinalFileNameKoEphemeride() {
    String fileName = "igw12810.sp3.Z";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }

  @Test
  public void TestFinalFileNameKoGpsWeek() {
    String fileName = "igsa2810.sp3.Z";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }

  @Test
  public void TestFinalFileNameKoDay() {
    String fileName = "igs1281x.sp3.Z";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }

  @Test
  public void TestFinalFileNameKoOrbitType() {
    String fileName = "igs12810.see.Z";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }

  @Test
  public void TestFinalFileNameKoCompressType() {
    String fileName = "igs12810.see.";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }

  // ultra-rapid (igu)
  @Test
  public void TestRapidFileNameOk() {
    String fileName = "igu12810_00.sp3.Z";
    Sp3FileName efmd = new Sp3FileName(fileName);
    assertNotNull(efmd);
  }

  @Test
  public void TestFileNameKoEphemeride() {
    String fileName = "igw12810_hr.sp3.Z";

    assertThrows(IllegalArgumentException.class, () -> {
      new Sp3FileName(fileName);
    });
  }
}
