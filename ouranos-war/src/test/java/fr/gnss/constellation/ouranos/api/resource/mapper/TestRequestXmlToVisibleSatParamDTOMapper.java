package fr.gnss.constellation.ouranos.api.resource.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.ZoneOffset;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class TestRequestXmlToVisibleSatParamDTOMapper {

	@Test
	public void testBeanVisibleSatParamToDTONull() {
		VisibleSateliteRequest source = null;
		VisibleSateliteRequestBean dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNull(dto);
	}

	@Test
	public void testBeanVisibleSatParamToDTOWithoutParam() {
		VisibleSateliteRequest source = new VisibleSateliteRequest();
		VisibleSateliteRequestBean dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNotNull(dto);
		assertNull(dto.getGeodeticCoordinate());
	}

	@Test
	public void testBeanVisibleSatParamToDTOCheckDateTime() throws Exception {
		VisibleSateliteRequest source = new VisibleSateliteRequest();

		XMLGregorianCalendar start = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		source.setStartDateOfMeasure(start);
		VisibleSateliteRequestBean dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNotNull(dto);
		assertNotNull(dto.getDateDebut());
		assertEquals(start.toGregorianCalendar().getTimeInMillis(), dto.getDateDebut().toInstant(ZoneOffset.UTC).toEpochMilli(), 0);
	}

}
