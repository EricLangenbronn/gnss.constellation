package fr.gnss.constellation.ouranos.api.controller.dto;

public interface IVisibleSatParam {

	Long getTimeStampStart();

	Long getTimeStampEnd();

	Double getLatitude();

	Double getLongitude();

	Double getAltitude();

	Double getElevationMask();

}
