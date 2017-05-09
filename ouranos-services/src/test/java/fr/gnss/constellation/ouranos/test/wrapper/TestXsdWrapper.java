package fr.gnss.constellation.ouranos.test.wrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.wrapper.XsdWrapper;
import fr.gnss.constellation.ouranos.xsd.request.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class TestXsdWrapper {

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Test
	public void testWrapVisibleSateliteRequest() throws Exception {

		VisibleSateliteRequest request = new VisibleSateliteRequest();
		request.setElevationMask(15);

		Date dateDebut = df.parse("2013-12-22 00:00:00");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateDebut);

		XMLGregorianCalendar XMLdateDebut = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
						cal.get(Calendar.DAY_OF_MONTH), dateDebut.getHours(), dateDebut.getMinutes(),
						dateDebut.getSeconds(), DatatypeConstants.FIELD_UNDEFINED, cal.getTimeZone().LONG)
				.normalize();

		Date dateFin = df.parse("2013-12-22 23:45:00");
		cal.setTime(dateFin);

		XMLGregorianCalendar XMLdateFin = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
						cal.get(Calendar.DAY_OF_MONTH), dateFin.getHours(), dateFin.getMinutes(), dateFin.getSeconds(),
						DatatypeConstants.FIELD_UNDEFINED, cal.getTimeZone().LONG)
				.normalize();

		request.setStartDateOfMeasure(XMLdateDebut);
		request.setEndDateOfMeasure(XMLdateFin);
		GeodeticCoordinate geo = new GeodeticCoordinate();
		geo.setLatitude(38.889139);
		geo.setLongitude(-77.049);
		geo.setAltitude(130.049);
		request.setGroundStation(geo);

		VisibleSateliteRequestBean res = XsdWrapper.wrapVisibleSateliteRequest(request);
		assertNotNull(res);
		assertEquals(0.2617993877, res.getRadElevationMask(), 0.00001);

	}
}
