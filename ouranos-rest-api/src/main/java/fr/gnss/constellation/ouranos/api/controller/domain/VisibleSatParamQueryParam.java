package fr.gnss.constellation.ouranos.api.controller.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisibleSatParamQueryParam implements IVisibleSatParam {

	@Valid
	@NotNull(message = "Timestamp start is mandatory")
	@ApiParam(value = "Timestamp date début en seconde", required = true)
	private Long startDateOfMeasure;

	@Valid
	@NotNull(message = "Timestamp end is mandatory")
	@ApiParam(value = "Timestamp date fin en seconde", required = true)
	private Long endDateOfMeasure;

	@Valid
	@NotNull(message = "Latitude is mandatory")
	private Double latitude;

	@Valid
	@NotNull(message = "Longitude is mandatory")
	private Double longitude;

	@Valid
	@NotNull(message = "Altitude is mandatory")
	private Double altitude;

	@Valid
	@NotNull(message = "Elevation mask is mandatory")
	private Double elevationMask;

	@Override
	public Long getTimeStampStart() {
		return startDateOfMeasure;
	}

	@Override
	public Long getTimeStampEnd() {
		return endDateOfMeasure;
	}

	@Override
	public Double getLatitude() {
		return latitude;
	}

	@Override
	public Double getLongitude() {
		return longitude;
	}

	@Override
	public Double getAltitude() {
		return altitude;
	}

	@Override
	public Double getElevationMask() {
		return elevationMask;
	}

}
