package fr.gnss.constellation.ouranos.api.controller.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class VisibleSatRequestDto {

    @NotNull(message = "Timestamp start is mandatory")
    @Parameter(description = "Timestamp date début en seconde", required = true)
    private Long startDateOfMeasure;

    @NotNull(message = "Timestamp end is mandatory")
    @Parameter(description = "Timestamp date fin en seconde", required = true)
    private Long endDateOfMeasure;

    @NotNull(message = "Latitude is mandatory")
    private Double latitude;

    @NotNull(message = "Longitude is mandatory")
    private Double longitude;

    @NotNull(message = "Altitude is mandatory")
    private Double altitude;

    @NotNull(message = "Elevation mask is mandatory")
    private Double elevationMask;

}
