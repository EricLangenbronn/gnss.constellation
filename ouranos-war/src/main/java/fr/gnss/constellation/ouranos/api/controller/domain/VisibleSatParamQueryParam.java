package fr.gnss.constellation.ouranos.api.controller.domain;

import javax.validation.constraints.NotBlank;

public class VisibleSatParamQueryParam implements IVisibleSatParam {

	@NotBlank(message = "Timestamp start is mandatory")
	private String tsStart;

	@NotBlank(message = "Timestamp end is mandatory")
	private String tsEnd;

	@NotBlank(message = "Latitude is mandatory")
	private String lat;

	@NotBlank(message = "Longitude is mandatory")
	private String longi;

	@NotBlank(message = "Altitude is mandatory")
	private String alt;

	@NotBlank(message = "Elevation mask is mandatory")
	private String elevmask;

	public String getTimeStampStart() {
		return tsStart;
	}

	public String getTimeStampEnd() {
		return tsEnd;
	}

	public String getLatitude() {
		return lat;
	}

	public String getLongitude() {
		return longi;
	}

	public String getAltitude() {
		return alt;
	}

	public String getElevationMask() {
		return elevmask;
	}

	public void setTsStart(String tsStart) {
		this.tsStart = tsStart;
	}

	public void setTsEnd(String tsEnd) {
		this.tsEnd = tsEnd;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setElevmask(String elevmask) {
		this.elevmask = elevmask;
	}

}
