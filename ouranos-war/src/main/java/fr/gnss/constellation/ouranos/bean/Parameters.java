package fr.gnss.constellation.ouranos.bean;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class Parameters {

	private LocalDateTime startMeasure;
	private LocalTime timeOfMeasure;
	private double elevationMask;
	private GeodeticCoordinate basePosition;

	public Parameters() {

	}

	public Parameters(LocalDateTime p_startMeasure, GeodeticCoordinate p_basePosition) {
		this(p_startMeasure, LocalTime.parse("23:45", DateTimeFormatter.ISO_TIME), 15.0, p_basePosition);
	}

	public Parameters(LocalDateTime p_startMeasure, LocalTime p_timeOfMeasure, double p_elevationMask,
			GeodeticCoordinate p_basePosition) {
		startMeasure = p_startMeasure;
		timeOfMeasure = p_timeOfMeasure;
		elevationMask = p_elevationMask;
		basePosition = p_basePosition;
	}

	public LocalDateTime getStartMeasure() {
		return startMeasure;
	}

	public void setStartMeasure(LocalDateTime startMeasure) {
		this.startMeasure = startMeasure;
	}

	public LocalTime getTimeOfMeasure() {
		return timeOfMeasure;
	}

	public void setTimeOfMeasure(LocalTime timeOfMeasure) {
		this.timeOfMeasure = timeOfMeasure;
	}

	public double getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(double elevationMask) {
		this.elevationMask = elevationMask;
	}

	public GeodeticCoordinate getBasePosition() {
		return basePosition;
	}

	public void setBasePosition(GeodeticCoordinate basePosition) {
		this.basePosition = basePosition;
	}

	@Override
	public String toString() {
		return "Parameters [startMeasure=" + startMeasure + ", timeOfMeasure=" + timeOfMeasure + ", elevationMask="
				+ elevationMask + ", basePosition=" + basePosition + "]";
	}
}
