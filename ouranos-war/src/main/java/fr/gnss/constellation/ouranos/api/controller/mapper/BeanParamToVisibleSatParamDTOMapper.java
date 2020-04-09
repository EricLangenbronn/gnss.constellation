package fr.gnss.constellation.ouranos.api.controller.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.api.controller.domain.IVisibleSatParam;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;

public class BeanParamToVisibleSatParamDTOMapper {

	private final static Logger LOGGER = LoggerFactory.getLogger(BeanParamToVisibleSatParamDTOMapper.class);

	public static VisibleSateliteRequestBean beanVisibleSatParamToDTO(IVisibleSatParam source) {
		return createBeanVisibleSatParamToDTO(source);
	}

	// -------------------- Methodes internes --------------------

	private static VisibleSateliteRequestBean createBeanVisibleSatParamToDTO(IVisibleSatParam source) {

		VisibleSateliteRequestBean dto = null;

		if (source != null) {

			try {
				dto = new VisibleSateliteRequestBean();
				if (source.getTimeStampStart() != null) {
					Long value = Long.parseLong(source.getTimeStampStart());
					Instant start = Instant.ofEpochSecond(value.longValue());
					dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
				}

				if (source.getTimeStampEnd() != null) {
					Long value = Long.parseLong(source.getTimeStampEnd());
					Instant fin = Instant.ofEpochSecond(value);
					dto.setDateFin(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
				}

				if (source.getElevationMask() != null) {
					Double value = Math.toRadians(Double.parseDouble(source.getElevationMask()));
					dto.setRadElevationMask(value);
				}

				if (source.getLatitude() != null && source.getLongitude() != null && source.getAltitude() != null) {
					Double lat = Double.parseDouble(source.getLatitude());
					Double longi = Double.parseDouble(source.getLongitude());
					Double alt = Double.parseDouble(source.getAltitude());

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
