package fr.gnss.constellation.ouranos.orbitdata.sp3;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestSp3FileNameUtils {

  @Test
  public void testSp3FileNameNull() throws Exception {
    LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(null);
    assertNull(dateDebut);
  }

  @Test
  public void testSp3FileNameDateTimeDebutDimanche() throws Exception {
    Sp3FileName sp3FileName = new Sp3FileName("igs17720.sp3");
    LocalDateTime expectedDate = LocalDateTime.of(2013, 12, 22, 0, 0, 0);

    LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);

    assertEquals(expectedDate, dateDebut);
  }

  @Test
  public void testSp3FileNameDateTimeDebutOtherDay() throws Exception {
    Sp3FileName sp3FileName = new Sp3FileName("igs18254.sp3");
    LocalDateTime expectedDate = LocalDateTime.of(2015, 1, 1, 0, 0, 0);

    LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);

    assertEquals(expectedDate, dateDebut);
  }

  @Test
  public void testSp3FileNameDateTimeEndDimanche() throws Exception {
    Sp3FileName sp3FileName = new Sp3FileName("igs17720.sp3");
    LocalDateTime expectedDate = LocalDateTime.of(2013, 12, 22, 23, 59, 0);

    LocalDateTime dateDebut = Sp3FileNameUtils.getEndDateTime(sp3FileName);

    assertEquals(expectedDate, dateDebut);
  }

  @Test
  public void testSp3FileNameDateTimeEndOtherDay() throws Exception {
    Sp3FileName sp3FileName = new Sp3FileName("igs18254.sp3");
    LocalDateTime expectedDate = LocalDateTime.of(2015, 1, 1, 23, 59, 0);

    LocalDateTime dateDebut = Sp3FileNameUtils.getEndDateTime(sp3FileName);

    assertEquals(expectedDate, dateDebut);
  }

  @Test
  public void testOrbitDataUtilsGetSp3FileName() throws Exception {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = formatter.parse("04/08/2004");

    LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    Sp3FileName res = Sp3FileNameUtils.getSp3FileName(EphemerideType.igs, localDate, OrbitType.sp3);

    Sp3FileName expected = new Sp3FileName(EphemerideType.igs, 1282, 3, -1, OrbitType.sp3);
    assertEquals(expected, res);
  }

  @Test
  public void testOrbitDataUtilsGetSp3FileNameSunday() throws Exception {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = formatter.parse("07/05/2017");

    LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    Sp3FileName res = Sp3FileNameUtils.getSp3FileName(EphemerideType.igs, localDate, OrbitType.sp3);

    Sp3FileName expected = new Sp3FileName(EphemerideType.igs, 1948, 0, -1, OrbitType.sp3);
    assertEquals(expected, res);
  }

  @Test
  public void testOrbitDataUtilsGetAllSp3FileNameBetween2Date2Day() throws Exception {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date dateDebut = formatter.parse("04/08/2004");
    Date dateFin = formatter.parse("06/08/2004");

    LocalDateTime localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    List<Sp3FileName> res = Sp3FileNameUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, localDateDebut,
        localDateFin, OrbitType.sp3);

    assertEquals(3, res.size());
  }

  @Test
  public void testOrbitDataUtilsGetAllSp3FileNameBetween2Date1Day() throws Exception {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date dateDebut = formatter.parse("03/04/2004");
    Date dateFin = formatter.parse("04/04/2004");

    LocalDateTime localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    List<Sp3FileName> res = Sp3FileNameUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, localDateDebut,
        localDateFin, OrbitType.sp3);

    assertEquals(2, res.size());
  }
}
