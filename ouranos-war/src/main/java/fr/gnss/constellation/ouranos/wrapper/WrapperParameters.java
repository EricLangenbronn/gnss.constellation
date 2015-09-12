package fr.gnss.constellation.ouranos.wrapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class WrapperParameters {

	public static fr.gnss.constellation.ouranos.model.Parameters wrapperParameter(Parameters param) {

		DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
		LocalDateTime startLocal = LocalDateTime.parse(param.getStartMeasure(), dateTimeformatter);

		DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("h:mm a");
		LocalTime duration = LocalTime.parse(param.getTimeOfMeasure(), timeformatter);

		LocalDateTime endLocal = startLocal.plusHours(duration.getHour()).plusMinutes(duration.getMinute());

		double longitude = Math.toRadians(Integer.parseInt(param.getLongitude()));
		double latitude = Math.toRadians(Integer.parseInt(param.getLatitude()));
		double altitude = Integer.parseInt(param.getAltitude());
		GeodeticCoordinate base = new GeodeticCoordinate(longitude, latitude, altitude);

		fr.gnss.constellation.ouranos.model.Parameters wrapParam = new fr.gnss.constellation.ouranos.model.Parameters(
				base, startLocal, endLocal);

		return wrapParam;
	}

}
