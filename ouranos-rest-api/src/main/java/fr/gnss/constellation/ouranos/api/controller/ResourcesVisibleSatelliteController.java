package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.api.controller.dto.VisibleSatRequestDto;
import fr.gnss.constellation.ouranos.api.controller.mapper.ResourcesVisibleSatByPeriodControllerMapper;
import fr.gnss.constellation.ouranos.domain.ElevationMask;
import fr.gnss.constellation.ouranos.domain.GroundStation;
import fr.gnss.constellation.ouranos.domain.ISateliteVisibleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/api/visibleSat/byPeriod", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
@Validated
public class ResourcesVisibleSatelliteController {

    private final ResourcesVisibleSatByPeriodControllerMapper resourcesVisibleSatByPeriodControllerMapper = Mappers.getMapper(ResourcesVisibleSatByPeriodControllerMapper.class);
    private final ISateliteVisibleService sateliteVisibleService;


    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds visible satellite")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Given visible satellite found")})
    public List<SatelliteDto> getVisibleSatellite(
            @Parameter(description = "submission request", required = true) @NotNull @Valid VisibleSatRequestDto visibleSatRequestDto) {

        return resourcesVisibleSatByPeriodControllerMapper.visiblesSatelitesDomainToDto(
                sateliteVisibleService.getSatelliteVisible(
                        GroundStation.builder()
                                .latitude(new GroundStation.Latitude(Math.toRadians(visibleSatRequestDto.latitude())))
                                .longitude(new GroundStation.Longitude(Math.toRadians(visibleSatRequestDto.longitude())))
                                .altitude(new GroundStation.Altitude(visibleSatRequestDto.altitude()))
                                .build(),
                        new ElevationMask(Math.toRadians(visibleSatRequestDto.elevationMask())),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(visibleSatRequestDto.startDateOfMeasure()), ZoneId.of("UTC")),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(visibleSatRequestDto.endDateOfMeasure()), ZoneId.of("UTC"))
                )
        );
    }

}
