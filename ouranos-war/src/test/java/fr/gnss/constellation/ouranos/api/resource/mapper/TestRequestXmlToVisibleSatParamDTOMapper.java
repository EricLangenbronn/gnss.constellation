package fr.gnss.constellation.ouranos.api.resource.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.ZoneOffset;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import fr.gnss.constellation.ouranos.service.resource.request.bean.VisibleSatParamDTO;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class TestRequestXmlToVisibleSatParamDTOMapper {

	@Test
	public void testBeanVisibleSatParamToDTONull() {
		VisibleSateliteRequest source = null;
		VisibleSatParamDTO dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNull(dto);
	}

	@Test
	public void testBeanVisibleSatParamToDTOWithoutParam() {
		VisibleSateliteRequest source = new VisibleSateliteRequest();
		VisibleSatParamDTO dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNotNull(dto);
		assertNull(dto.getGeodeticCoordinate());
	}

	@Test
	public void testBeanVisibleSatParamToDTOCheckDateTime() throws Exception {
		VisibleSateliteRequest source = new VisibleSateliteRequest();

		XMLGregorianCalendar start = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		source.setStartDateOfMeasure(start);
		VisibleSatParamDTO dto = RequestXmlToVisibleSatParamDTOMapper.beanVisibleSateliteRequestToDTO(source);

		assertNotNull(dto);
		assertNotNull(dto.getDateDebut());
		assertEquals(start.toGregorianCalendar().getTimeInMillis(), dto.getDateDebut().toInstant(ZoneOffset.UTC).toEpochMilli(), 0);
	}

}
