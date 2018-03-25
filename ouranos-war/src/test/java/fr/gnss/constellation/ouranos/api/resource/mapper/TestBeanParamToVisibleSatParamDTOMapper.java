package fr.gnss.constellation.ouranos.api.resource.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import fr.gnss.constellation.ouranos.api.resource.bean.VisibleSatParam;
import fr.gnss.constellation.ouranos.service.resource.request.bean.VisibleSatParamDTO;

public class TestBeanParamToVisibleSatParamDTOMapper {

	@Test
	public void testBeanVisibleSatParamToDTONull() {
		VisibleSatParam source = null;
		VisibleSatParamDTO dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNull(dto);
	}

	@Test
	public void testBeanVisibleSatParamToDTOWithoutParam() {
		VisibleSatParam source = new VisibleSatParam();
		VisibleSatParamDTO dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNull(dto.getGeodeticCoordinate());
	}

	@Test
	public void testBeanVisibleSatParamToDTOCheckDateTime() {
		VisibleSatParam source = new VisibleSatParam();

		LocalDateTime start = LocalDateTime.now(Clock.systemUTC());
		source.setTimeStampStart(start.toInstant(ZoneOffset.UTC).toEpochMilli());
		VisibleSatParamDTO dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNotNull(dto.getDateDebut());
		assertEquals(start.toInstant(ZoneOffset.UTC).toEpochMilli(), dto.getDateDebut().toInstant(ZoneOffset.UTC).toEpochMilli(), 0);
	}

}
