package fr.gnss.constellation.ouranos.service.resource.request.bean;

import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class VisibleSatParamDTO {

	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private GeodeticCoordinate geodeticCoordinate;
	private Double radElevationMask;

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

	public Double getRadElevationMask() {
		return radElevationMask;
	}

	public void setRadElevationMask(Double radElevationMask) {
		this.radElevationMask = radElevationMask;
	}

}
