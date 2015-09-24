package fr.gnss.constellation.ouranos.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Parameters {

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/mm/yyyy l:MM a")
	private String startOfMeasure;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/mm/yyyy l:MM a")
	private String endOfMeasure;
	@NotNull
	@Size(min = 0, max = 90, message = "{elevation.size}")
	private double elevationMask;

	@NotNull
	@Size(min = -180, max = 180, message = "{longitude.size}")
	private String longitude;

	@NotNull
	@Size(min = -90, max = 90, message = "{latitude.size}")
	private String latitude;

	@NotNull
	@Size(min = -11034, max = 8850, message = "{altitude.size}")
	private String altitude;

	public String getStartOfMeasure() {
		return startOfMeasure;
	}

	public void setStartOfMeasure(String startMeasure) {
		this.startOfMeasure = startMeasure;
	}

	public String getEndOfMeasure() {
		return endOfMeasure;
	}

	public void setEndOfMeasure(String endMeasure) {
		this.endOfMeasure = endMeasure;
	}

	public double getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(double elevationMask) {
		this.elevationMask = elevationMask;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@Override
	public String toString() {
		return "Parameters [startMeasure=" + startOfMeasure + ", endOfMeasure=" + endOfMeasure + ", elevationMask="
				+ elevationMask + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude
				+ "]";
	}

}
