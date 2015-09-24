package fr.gnss.constellation.ouranos.wrapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class WrapperParameters {

	public static fr.gnss.constellation.ouranos.model.Parameters wrapperParameter(Parameters param) {

		DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
		LocalDateTime startLocal = LocalDateTime.parse(param.getStartOfMeasure(), dateTimeformatter);

		LocalDateTime endLocal = LocalDateTime.parse(param.getEndOfMeasure(), dateTimeformatter);

		double longitude = Math.toRadians(Double.parseDouble(param.getLongitude()));
		double latitude = Math.toRadians(Double.parseDouble(param.getLatitude()));
		double altitude = Double.parseDouble(param.getAltitude());
		GeodeticCoordinate base = new GeodeticCoordinate(longitude, latitude, altitude);

		fr.gnss.constellation.ouranos.model.Parameters wrapParam = new fr.gnss.constellation.ouranos.model.Parameters(
				base, startLocal, endLocal);

		return wrapParam;
	}

}
