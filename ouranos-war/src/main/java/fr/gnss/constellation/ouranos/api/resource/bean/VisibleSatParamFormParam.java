package fr.gnss.constellation.ouranos.api.resource.bean;

import javax.ws.rs.FormParam;

public class VisibleSatParamFormParam implements IVisibleSatParam {

	@FormParam("tsStart")
	private String timeStampStart;
	@FormParam("tsEnd")
	private String timeStampEnd;
	@FormParam("lat")
	private String latitude;
	@FormParam("long")
	private String longitude;
	@FormParam("alt")
	private String altitude;
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
