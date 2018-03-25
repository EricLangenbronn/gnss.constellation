package fr.gnss.constellation.ouranos.api.resource.bean;

import javax.ws.rs.QueryParam;

public class VisibleSatParam {

	@QueryParam("tsStart")
	private Long timeStampStart;
	@QueryParam("tsEnd")
	private Long timeStampEnd;
	@QueryParam("lat")
	private Double latitude;
	@QueryParam("long")
	private Double longitude;
	@QueryParam("alt")
	private Double altitude;
	@QueryParam("elevmask")
	private Double elevationMask;

	public Long getTimeStampStart() {
		return timeStampStart;
	}

	public void setTimeStampStart(Long timeStampStart) {
		this.timeStampStart = timeStampStart;
	}

	public Long getTimeStampEnd() {
		return timeStampEnd;
	}

	public void setTimeStampEnd(Long timeStampEnd) {
		this.timeStampEnd = timeStampEnd;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(Double elevationMask) {
		this.elevationMask = elevationMask;
	}

}
