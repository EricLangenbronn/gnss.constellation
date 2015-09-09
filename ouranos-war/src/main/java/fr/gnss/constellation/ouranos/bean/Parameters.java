package fr.gnss.constellation.ouranos.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Parameters {

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/mm/yyyy l:MM")
	private String startMeasure;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "HH:MM")
	private String timeOfMeasure;

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

	public String getStartMeasure() {
		return startMeasure;
	}

	public void setStartMeasure(String startMeasure) {
		this.startMeasure = startMeasure;
	}

	public String getTimeOfMeasure() {
		return timeOfMeasure;
	}

	public void setTimeOfMeasure(String timeOfMeasure) {
		this.timeOfMeasure = timeOfMeasure;
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
		return "Parameters [startMeasure=" + startMeasure + ", timeOfMeasure=" + timeOfMeasure + ", elevationMask="
				+ elevationMask + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude
				+ "]";
	}

}
