package fr.gnss.constellation.ouranos.api.controller.domain;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.FormParam;

public class VisibleSatParamFormParam implements IVisibleSatParam {

	@NotBlank(message = "Timestamp start is mandatory")
	@FormParam("tsStart")
	private String timeStampStart;

	@NotBlank(message = "Timestamp end is mandatory")
	@FormParam("tsEnd")
	private String timeStampEnd;

	@NotBlank(message = "Latitude is mandatory")
	@FormParam("lat")
	private String latitude;

	@NotBlank(message = "Longitude is mandatory")
	@FormParam("long")
	private String longitude;

	@NotBlank(message = "Altitude is mandatory")
	@FormParam("alt")
	private String altitude;

	@NotBlank(message = "Elevation mask is mandatory")
	@FormParam("elevmask")
	private String elevationMask;

	public String getTimeStampStart() {
		return timeStampStart;
	}

	public void setTimeStampStart(String timeStampStart) {
		this.timeStampStart = timeStampStart;
	}

	public String getTimeStampEnd() {
		return timeStampEnd;
	}

	public void setTimeStampEnd(String timeStampEnd) {
		this.timeStampEnd = timeStampEnd;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(String elevationMask) {
		this.elevationMask = elevationMask;
	}

}
