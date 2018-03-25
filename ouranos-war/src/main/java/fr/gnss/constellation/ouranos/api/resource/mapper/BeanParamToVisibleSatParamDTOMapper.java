package fr.gnss.constellation.ouranos.api.resource.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import fr.gnss.constellation.ouranos.api.resource.bean.VisibleSatParam;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.resource.request.bean.VisibleSatParamDTO;

public class BeanParamToVisibleSatParamDTOMapper {

	public static VisibleSatParamDTO beanVisibleSatParamToDTO(VisibleSatParam source) {
		return createBeanVisibleSatParamToDTO(source);
	}

	// -------------------- Methodes internes --------------------

	private static VisibleSatParamDTO createBeanVisibleSatParamToDTO(VisibleSatParam source) {

		VisibleSatParamDTO dto = null;

		if (source != null) {

			dto = new VisibleSatParamDTO();
			if (source.getTimeStampStart() != null) {
				Instant start = Instant.ofEpochMilli(source.getTimeStampStart());
				dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
			}

			if (source.getTimeStampEnd() != null) {
				Instant fin = Instant.ofEpochMilli(source.getTimeStampEnd());
				dto.setDateDebut(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
			}

			dto.setRadElevationMask(source.getElevationMask());

			if (source.getLatitude() != null && source.getLongitude() != null && source.getAltitude() != null) {
				GeodeticCoordinate geoDTO = new GeodeticCoordinate(source.getLatitude(), source.getLongitude(), source.getAltitude());
				dto.setGeodeticCoordinate(geoDTO);
			}
		}

		return dto;
	}
}
