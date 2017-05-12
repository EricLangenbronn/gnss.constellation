package fr.gnss.constellation.ouranos.wrapper;

import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class XsdWrapper {

	public static fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate wrapGeodeticCoordindate(
			fr.gnss.constellation.ouranos.xsd.request.GeodeticCoordinate xsdGeodetic) {

		fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate geodeticCoordinate;

		geodeticCoordinate = new fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate(
				Math.toRadians(xsdGeodetic.getLatitude()), Math.toRadians(xsdGeodetic.getLongitude()),
				xsdGeodetic.getAltitude());

		return geodeticCoordinate;
	}

	public static VisibleSateliteRequestBean wrapVisibleSateliteRequest(VisibleSateliteRequest p_request) {

		VisibleSateliteRequestBean res = new VisibleSateliteRequestBean();

		res.setDateDebut(p_request.getStartDateOfMeasure().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
		res.setDateFin(p_request.getEndDateOfMeasure().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
		res.setGeodeticCoordinate(XsdWrapper.wrapGeodeticCoordindate(p_request.getGroundStation()));

		res.setRadElevationMask(Math.toRadians(p_request.getElevationMask()));

		return res;
	}

}
