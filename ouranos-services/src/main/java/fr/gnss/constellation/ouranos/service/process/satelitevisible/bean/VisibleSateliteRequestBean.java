package fr.gnss.constellation.ouranos.service.process.satelitevisible.bean;

import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class VisibleSateliteRequestBean {

	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private GeodeticCoordinate geodeticCoordinate;
	private double radElevationMask;

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public GeodeticCoordinate getGeodeticCoordinate() {
		return geodeticCoordinate;
	}

	public void setGeodeticCoordinate(GeodeticCoordinate geodeticCoordinate) {
		this.geodeticCoordinate = geodeticCoordinate;
	}

	public double getRadElevationMask() {
		return radElevationMask;
	}

	public void setRadElevationMask(double radElevationMask) {
		this.radElevationMask = radElevationMask;
	}

}
