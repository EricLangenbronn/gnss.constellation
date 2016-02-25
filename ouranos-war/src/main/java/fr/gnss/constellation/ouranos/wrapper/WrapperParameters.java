package fr.gnss.constellation.ouranos.wrapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class WrapperParameters {

	public static fr.gnss.constellation.ouranos.model.Parameters wrapperParameter(Parameters param) {

		DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
		LocalDateTime startLocal = param.getStartOfMeasure();

		LocalDateTime endLocal = param.getEndOfMeasure();

		double longitude = Math.toRadians(param.getLongitude());
		double latitude = Math.toRadians(param.getLatitude());
		double altitude = param.getAltitude();
		GeodeticCoordinate base = new GeodeticCoordinate(longitude, latitude, altitude);

		fr.gnss.constellation.ouranos.model.Parameters wrapParam = new fr.gnss.constellation.ouranos.model.Parameters(
				base, startLocal, endLocal);

		return wrapParam;
	}

}
