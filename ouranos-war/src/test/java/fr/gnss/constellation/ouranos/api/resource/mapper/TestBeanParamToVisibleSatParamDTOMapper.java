package fr.gnss.constellation.ouranos.api.resource.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import fr.gnss.constellation.ouranos.api.resource.bean.VisibleSatParamQueryParam;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;

public class TestBeanParamToVisibleSatParamDTOMapper {

	@Test
	public void testBeanVisibleSatParamToDTONull() {
		VisibleSatParamQueryParam source = null;
		VisibleSateliteRequestBean dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNull(dto);
	}

	@Test
	public void testBeanVisibleSatParamToDTOWithoutParam() {
		VisibleSatParamQueryParam source = new VisibleSatParamQueryParam();
		VisibleSateliteRequestBean dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNull(dto.getGeodeticCoordinate());
	}

	@Test
	public void testBeanVisibleSatParamToDTOCheckDateTime() {
		VisibleSatParamQueryParam source = new VisibleSatParamQueryParam();

		LocalDateTime start = LocalDateTime.now(Clock.systemUTC());
		source.setTimeStampStart(String.valueOf(start.toInstant(ZoneOffset.UTC).getEpochSecond()));
		VisibleSateliteRequestBean dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNotNull(dto.getDateDebut());
		assertEquals(start.toInstant(ZoneOffset.UTC).getEpochSecond(), dto.getDateDebut().toInstant(ZoneOffset.UTC).getEpochSecond(), 0);
	}

}
