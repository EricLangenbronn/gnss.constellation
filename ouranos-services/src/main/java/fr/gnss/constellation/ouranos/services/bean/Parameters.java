package fr.gnss.constellation.ouranos.services.bean;

import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class Parameters {

	private GeodeticCoordinate baseCoordiante;
	private LocalDateTime startOfMeasure;
	private LocalDateTime timeOfMeasure;

	public Parameters(GeodeticCoordinate baseCoordiante, LocalDateTime startOfMeasure, LocalDateTime timeOfMeasure) {
		super();
		this.baseCoordiante = baseCoordiante;
		this.startOfMeasure = startOfMeasure;
		this.timeOfMeasure = timeOfMeasure;
	}

	public GeodeticCoordinate getBaseCoordiante() {
		return baseCoordiante;
	}

	public void setBaseCoordiante(GeodeticCoordinate baseCoordiante) {
		this.baseCoordiante = baseCoordiante;
	}

	public LocalDateTime getStartOfMeasure() {
		return startOfMeasure;
	}

	public void setStartOfMeasure(LocalDateTime startOfMeasure) {
		this.startOfMeasure = startOfMeasure;
	}

	public LocalDateTime getTimeOfMeasure() {
		return timeOfMeasure;
	}

	public void setTimeOfMeasure(LocalDateTime timeOfMeasure) {
		this.timeOfMeasure = timeOfMeasure;
	}

}
