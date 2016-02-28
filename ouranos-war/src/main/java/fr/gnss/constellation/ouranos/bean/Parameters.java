package fr.gnss.constellation.ouranos.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Parameters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1708054331528536487L;

	@DateTimeFormat(pattern="MM/dd/yyyy h:mm a")
	@NotNull
	private LocalDateTime startOfMeasure;

	@DateTimeFormat(pattern="MM/dd/yyyy h:mm a")
	@NotNull
	private LocalDateTime endOfMeasure;

	@NotNull
	@DecimalMax("90.0")
	@DecimalMin("0.0")
	private double elevationMask;

	@NotNull
	@DecimalMax("180.0")
	@DecimalMin("-180.0")
	private double longitude;

	@NotNull
	@DecimalMax("90.0")
	@DecimalMin("-90.0")
	private double latitude;

	@NotNull
	@DecimalMax("8850.0")
	@DecimalMin("-11034.0")
	private double altitude;

	public LocalDateTime getStartOfMeasure() {
		return startOfMeasure;
	}

	public void setStartOfMeasure(LocalDateTime startMeasure) {
		this.startOfMeasure = startMeasure;
	}

	public LocalDateTime getEndOfMeasure() {
		return endOfMeasure;
	}

	public void setEndOfMeasure(LocalDateTime endMeasure) {
		this.endOfMeasure = endMeasure;
	}

	public double getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(double elevationMask) {
		this.elevationMask = elevationMask;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	@Override
	public String toString() {
		return "Parameters [startMeasure=" + startOfMeasure + ", endOfMeasure=" + endOfMeasure + ", elevationMask="
				+ elevationMask + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude
				+ "]";
	}

}
