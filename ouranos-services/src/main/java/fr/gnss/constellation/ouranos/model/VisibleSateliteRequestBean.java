package fr.gnss.constellation.ouranos.model;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import fr.gnss.constellation.ouranos.xsd.request.GeodeticCoordinate;

public class VisibleSateliteRequestBean {

	private GeodeticCoordinate groundStation;
	private XMLGregorianCalendar startDateOfMeasure;
	private XMLGregorianCalendar endDateOfMeasure;
	private double elevationMask;

	public GeodeticCoordinate getGroundStation() {
		return groundStation;
	}

	public void setGroundStation(GeodeticCoordinate groundStation) {
		this.groundStation = groundStation;
	}

	public XMLGregorianCalendar getStartDateOfMeasure() {
		return startDateOfMeasure;
	}

	public void setStartDateOfMeasure(XMLGregorianCalendar startDateOfMeasure) {
		this.startDateOfMeasure = startDateOfMeasure;
	}

	public XMLGregorianCalendar getEndDateOfMeasure() {
		return endDateOfMeasure;
	}

	public void setEndDateOfMeasure(XMLGregorianCalendar endDateOfMeasure) {
		this.endDateOfMeasure = endDateOfMeasure;
	}

	public double getElevationMask() {
		return elevationMask;
	}

	public void setElevationMask(double elevationMask) {
		this.elevationMask = elevationMask;
	}

}
