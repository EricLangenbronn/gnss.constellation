package fr.gnss.constellation.ouranos.api.controller.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.api.controller.domain.IVisibleSatParam;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;

public class BeanParamToVisibleSatParamDTOMapper {

	private final static Logger LOGGER = LoggerFactory.getLogger(BeanParamToVisibleSatParamDTOMapper.class);

	public static VisibleSateliteRequestDto beanVisibleSatParamToDTO(IVisibleSatParam source) {
		return createBeanVisibleSatParamToDTO(source);
	}

	// -------------------- Methodes internes --------------------

	private static VisibleSateliteRequestDto createBeanVisibleSatParamToDTO(IVisibleSatParam source) {

		VisibleSateliteRequestDto dto = null;

		if (source != null) {

			try {
				dto = new VisibleSateliteRequestDto();
				if (source.getTimeStampStart() != null) {
					Instant start = Instant.ofEpochSecond(source.getTimeStampStart());
					dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
				}

				if (source.getTimeStampEnd() != null) {
					Instant fin = Instant.ofEpochSecond(source.getTimeStampEnd());
					dto.setDateFin(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
				}

				if (source.getElevationMask() != null) {
					Double value = Math.toRadians(source.getElevationMask());
					dto.setRadElevationMask(value);
				}

				if (source.getLatitude() != null && source.getLongitude() != null && source.getAltitude() != null) {
					Double lat = source.getLatitude();
					Double longi = source.getLongitude();
					Double alt = source.getAltitude();

					GeodeticCoordinate geoDTO = new GeodeticCoordinate(lat.doubleValue(), longi.doubleValue(), alt.doubleValue());
					dto.setGeodeticCoordinate(geoDTO);
				}
			} catch (NumberFormatException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return dto;
	}
}
