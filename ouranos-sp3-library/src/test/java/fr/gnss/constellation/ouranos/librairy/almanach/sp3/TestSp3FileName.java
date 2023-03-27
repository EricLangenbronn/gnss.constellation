package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import org.junit.jupiter.api.Test;

public class TestSp3FileName {

  @Test
  public void testGenerationFileNameIgs() throws Exception {
    Sp3FileName sp3File = new Sp3FileName(EphemerideType.igs, 1282L, 1, -1, OrbitType.sp3);
    assertEquals("igs12821.sp3.Z", sp3File.getFileName(true));
  }

  @Test
  public void testGenerationFileNameIgu() throws Exception {
    Sp3FileName sp3File = new Sp3FileName(EphemerideType.igu, 1282L, 1, 0, OrbitType.sp3);
    assertEquals("igu12821_00.sp3.Z", sp3File.getFileName(true));
  }
}
