package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.api.controller.mapper.ResourcesVisibleSatByPeriodControllerMapper;
import fr.gnss.constellation.ouranos.domain.ElevationMask;
import fr.gnss.constellation.ouranos.domain.GroundStation;
import fr.gnss.constellation.ouranos.domain.ISateliteVisibleService;
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

@Path("/api/visibleSat/byPeriod")
@RequiredArgsConstructor
public class ResourcesVisibleSatelliteController {

    private final ResourcesVisibleSatByPeriodControllerMapper resourcesVisibleSatByPeriodControllerMapper = Mappers.getMapper(ResourcesVisibleSatByPeriodControllerMapper.class);
    private final ISateliteVisibleService sateliteVisibleService;

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SatelliteDto> getVisibleSatellite(@NotNull @QueryParam("startDateOfMeasure") Long startDateOfMeasure, @NotNull @QueryParam("endDateOfMeasure") Long endDateOfMeasure
            , @NotNull @QueryParam("latitude") Double latitude, @NotNull @QueryParam("longitude") Double longitude, @NotNull @QueryParam("altitude") Double altitude
            , @NotNull @QueryParam("elevationMask") Double elevationMask) {

        return resourcesVisibleSatByPeriodControllerMapper.visiblesSatelitesDomainToDto(
                sateliteVisibleService.getSatelliteVisible(
                        GroundStation.builder()
                                .latitude(new GroundStation.Latitude(Math.toRadians(latitude)))
                                .longitude(new GroundStation.Longitude(Math.toRadians(longitude)))
                                .altitude(new GroundStation.Altitude(altitude))
                                .build(),
                        new ElevationMask(Math.toRadians(elevationMask)),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(startDateOfMeasure), ZoneId.of("UTC")),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(endDateOfMeasure), ZoneId.of("UTC"))
                )
        );
    }

}