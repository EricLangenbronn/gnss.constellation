package fr.gnss.constellation.ouranos.test.api.controller.mapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import fr.gnss.constellation.ouranos.api.controller.dto.VisibleSatParamQueryParamDto;
import fr.gnss.constellation.ouranos.api.controller.mapper.BeanParamToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.domain.VisibleSateliteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBeanParamToVisibleSatParamDTOMapper {

	@Test
	public void testBeanVisibleSatParamToDTONull() {
		VisibleSatParamQueryParamDto source = null;
		VisibleSateliteRequest dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNull(dto);
	}

	@Test
	public void testBeanVisibleSatParamToDTOWithoutParam() {
		VisibleSatParamQueryParamDto source = new VisibleSatParamQueryParamDto();
		VisibleSateliteRequest dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNull(dto.getGeodeticCoordinate());
	}

	@Test
	public void testBeanVisibleSatParamToDTOCheckDateTime() {
		VisibleSatParamQueryParamDto source = new VisibleSatParamQueryParamDto();

		LocalDateTime start = LocalDateTime.now(Clock.systemUTC());
		source.setStartDateOfMeasure(start.toInstant(ZoneOffset.UTC).getEpochSecond());
		VisibleSateliteRequest dto = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(source);

		assertNotNull(dto);
		assertNotNull(dto.getDateDebut());
		assertEquals(start.toInstant(ZoneOffset.UTC).getEpochSecond(), dto.getDateDebut().toInstant(ZoneOffset.UTC).getEpochSecond(), 0);
	}

}
