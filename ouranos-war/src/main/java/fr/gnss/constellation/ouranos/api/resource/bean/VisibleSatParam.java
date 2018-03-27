package fr.gnss.constellation.ouranos.api.resource.bean;

import javax.ws.rs.QueryParam;

public class VisibleSatParam {

	@QueryParam("tsStart")
	private String timeStampStart;
	@QueryParam("tsEnd")
	private String timeStampEnd;
	@QueryParam("lat")
	private String latitude;
	@QueryParam("long")
	private String longitude;
	@QueryParam("alt")
	private String altitude;
	@QueryParam("elevmask")
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
