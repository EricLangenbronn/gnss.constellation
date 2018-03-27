package fr.gnss.constellation.ouranos.api.resource.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class RequestXmlToVisibleSatParamDTOMapper {

	public static VisibleSateliteRequestBean beanVisibleSateliteRequestToDTO(VisibleSateliteRequest source) {
		return createBeanVisibleSateliteRequestToDTO(source);
	}

	// -------------------- Methodes internes --------------------

	private static VisibleSateliteRequestBean createBeanVisibleSateliteRequestToDTO(VisibleSateliteRequest source) {
		VisibleSateliteRequestBean dto = null;

		if (source != null) {
			dto = new VisibleSateliteRequestBean();

			if (source.getStartDateOfMeasure() != null) {
				Instant start = Instant.ofEpochMilli(source.getStartDateOfMeasure().toGregorianCalendar().getTimeInMillis());
				dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
			}

			if (source.getStartDateOfMeasure() != null) {
				Instant fin = Instant.ofEpochMilli(source.getStartDateOfMeasure().toGregorianCalendar().getTimeInMillis());
				dto.setDateDebut(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
			}

			dto.setRadElevationMask(source.getElevationMask());

			if (source.getGroundStation() != null) {
				GeodeticCoordinate geoDTO = new GeodeticCoordinate(source.getGroundStation().getLatitude(),
						source.getGroundStation().getLongitude(), source.getGroundStation().getAltitude());
				dto.setGeodeticCoordinate(geoDTO);
			}
		}

		return dto;
	}
}
