package fr.gnss.constellation.ouranos.api.controller.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import fr.gnss.constellation.ouranos.api.controller.dto.IVisibleSatParam;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.domain.VisibleSateliteRequest;

public class BeanParamToVisibleSatParamDTOMapper {

	public static VisibleSateliteRequest beanVisibleSatParamToDTO(IVisibleSatParam source) {
		return createBeanVisibleSatParamToDTO(source);
	}

	// -------------------- Methodes internes --------------------

	private static VisibleSateliteRequest createBeanVisibleSatParamToDTO(IVisibleSatParam source) {

		VisibleSateliteRequest dto = null;

		if (source != null) {

			dto = new VisibleSateliteRequest();
			if (source.getTimeStampStart() != null) {
				Instant start = Instant.ofEpochSecond(source.getTimeStampStart());
				dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
			}

			if (source.getTimeStampEnd() != null) {
				Instant fin = Instant.ofEpochSecond(source.getTimeStampEnd());
				dto.setDateFin(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
			}

			if (source.getTimeStampStart() != null && source.getTimeStampEnd() != null) {
				if (dto.getDateDebut().isAfter(dto.getDateFin())) {
					throw new IllegalArgumentException("La date de debut doit être avant la date de fin");
				}
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
		}

		return dto;
	}
}
