package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.api.controller.mapper.ResourcesVisibleSatByPeriodControllerMapper;
import fr.gnss.constellation.ouranos.domain.satellite.ElevationMask;
import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import fr.gnss.constellation.ouranos.domain.satellite.visible.SatelliteVisibleService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Path("/api/visibleSat")
@RequiredArgsConstructor
public class ResourcesVisibleSatelliteController {

    private final ResourcesVisibleSatByPeriodControllerMapper resourcesVisibleSatByPeriodControllerMapper = Mappers.getMapper(ResourcesVisibleSatByPeriodControllerMapper.class);
    private final SatelliteVisibleService sateliteVisibleService;

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SatelliteDto> getVisibleSatellite(@NotNull @QueryParam("startDateOfMeasure") Long startTimestampOfMeasure, @NotNull @QueryParam("endDateOfMeasure") Long endTimestampOfMeasure
            , @NotNull @QueryParam("latitude") Double latitudeDegree, @NotNull @QueryParam("longitude") Double longitudeDegree, @NotNull @QueryParam("altitude") Double altitudeMeter
            , @NotNull @QueryParam("elevationMask") Double elevationMaskDegree) {

        return resourcesVisibleSatByPeriodControllerMapper.visiblesSatelitesDomainToDto(
                sateliteVisibleService.getSatelliteVisible(
                        GroundStation.builder()
                                .latitude(new GroundStation.Latitude(Math.toRadians(latitudeDegree)))
                                .longitude(new GroundStation.Longitude(Math.toRadians(longitudeDegree)))
                                .altitude(new GroundStation.Altitude(altitudeMeter))
                                .build(),
                        new ElevationMask(Math.toRadians(elevationMaskDegree)),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(startTimestampOfMeasure), ZoneId.of("UTC")),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(endTimestampOfMeasure), ZoneId.of("UTC"))
                )
        );
    }

}