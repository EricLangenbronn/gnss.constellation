package fr.gnss.constellation.ouranos.model;

import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class Parameters {

	private GeodeticCoordinate baseCoordiante;
	private double radElevationMask;
	private LocalDateTime startOfMeasure;
	private LocalDateTime endOfMeasure;

	public Parameters(GeodeticCoordinate baseCoordiante,  double radElevationMask, LocalDateTime startOfMeasure, LocalDateTime endOfMeasure) {
		super();
		this.baseCoordiante = baseCoordiante;
		this.startOfMeasure = startOfMeasure;
		this.endOfMeasure = endOfMeasure;
	}

	public GeodeticCoordinate getBaseCoordiante() {
		return baseCoordiante;
	}

	public double getRadElevationMask() {
		return radElevationMask;
	}

	public LocalDateTime getStartOfMeasure() {
		return startOfMeasure;
	}

	public LocalDateTime getEndOfMeasure() {
		return endOfMeasure;
	}

}
