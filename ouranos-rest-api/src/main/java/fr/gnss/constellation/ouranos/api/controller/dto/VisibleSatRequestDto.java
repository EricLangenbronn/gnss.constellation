package fr.gnss.constellation.ouranos.api.controller.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Validated
public record VisibleSatRequestDto(
        @NotNull(message = "Timestamp start is mandatory")
        @Parameter(description = "Timestamp date début en seconde", required = true)
        Long startDateOfMeasure,

        @NotNull(message = "Timestamp end is mandatory")
        @Parameter(description = "Timestamp date fin en seconde", required = true)
        Long endDateOfMeasure,

        @NotNull(message = "Latitude is mandatory")
        Double latitude,

        @NotNull(message = "Longitude is mandatory")
        Double longitude,

        @NotNull(message = "Altitude is mandatory")
        Double altitude,

        @NotNull(message = "Elevation mask is mandatory")
        Double elevationMask
) {

}
