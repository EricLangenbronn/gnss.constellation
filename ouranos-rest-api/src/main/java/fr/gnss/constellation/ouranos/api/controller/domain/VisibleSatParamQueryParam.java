package fr.gnss.constellation.ouranos.api.controller.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisibleSatParamQueryParam implements IVisibleSatParam {

	@Valid
	@NotBlank(message = "Timestamp start is mandatory")
	private String tsStart;

	@Valid
	@NotBlank(message = "Timestamp end is mandatory")
	private String tsEnd;

	@Valid
	@NotBlank(message = "Latitude is mandatory")
	private String lat;

	@Valid
	@NotBlank(message = "Longitude is mandatory")
	private String longi;

	@Valid
	@NotBlank(message = "Altitude is mandatory")
	private String alt;

	@Valid
	@NotBlank(message = "Elevation mask is mandatory")
	private String elevmask;

	public String getTimeStampStart() {
		return tsStart;
	}

	public String getTimeStampEnd() {
		return tsEnd;
	}

	public String getLatitude() {
		return lat;
	}

	public String getLongitude() {
		return longi;
	}

	public String getAltitude() {
		return alt;
	}

	public String getElevationMask() {
		return elevmask;
	}

}
