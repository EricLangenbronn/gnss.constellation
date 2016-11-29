package fr.gnss.constellation.ouranos.services;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.service.orbitdata.OrbitDataUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-dao-test.xml", "/moduleTest/ouranos-services-test.xml" })
public class TestOrbitDataUtils {

	@Test
	public void testOrbitDataUtils1() throws Exception {
		// 4 aout 2008 // 1282

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("04/08/2004");

		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		long res = OrbitDataUtils.getGpsWeek(localDate);
		assertEquals(1282, res);

	}

}
