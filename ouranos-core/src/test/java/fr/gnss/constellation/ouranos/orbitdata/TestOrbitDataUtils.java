package fr.gnss.constellation.ouranos.orbitdata;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TestOrbitDataUtils {

  @Test
  public void testOrbitDataUtilsGetGpsWeek() throws Exception {
    // 4 aout 2008 // 1282

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = formatter.parse("04/08/2004");

    LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    long res = OrbitDataUtils.getGpsWeek(localDate);
    assertEquals(1282, res);

  }
}
