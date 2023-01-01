package fr.gnss.constellation.ouranos.api.controller.satellitevisible;

import fr.gnss.constellation.ouranos.api.controller.satellitevisible.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.api.controller.satellitevisible.mapper.ResourcesVisibleSatByPeriodControllerMapper;
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
public class VisibleSatelliteController {

    private final ResourcesVisibleSatByPeriodControllerMapper resourcesVisibleSatByPeriodControllerMapper = Mappers.getMapper(ResourcesVisibleSatByPeriodControllerMapper.class);
    private final SatelliteVisibleService sateliteVisibleService;

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SatelliteDto> getVisibleSatellite(@NotNull @QueryParam("startDateOfMeasure") Long startTimestampOfMeasure, @NotNull @QueryParam("endDateOfMeasure") Long endTimestampOfMeasure
            , @NotNull @QueryParam("decimalLatitudeDegree") Double decimalLatitudeDegree, @NotNull @QueryParam("decimalLongitudeDegree") Double decimalLongitudeDegree, @NotNull @QueryParam("altitude") Double altitudeMeter
            , @NotNull @QueryParam("elevationMask") Double elevationMaskDegree) {

        return resourcesVisibleSatByPeriodControllerMapper.visiblesSatelitesDomainToDto(
                sateliteVisibleService.getSatelliteVisible(
                        GroundStation.builder()
                                .latitude(new GroundStation.Latitude(Math.toRadians(decimalLatitudeDegree)))
                                .longitude(new GroundStation.Longitude(Math.toRadians(decimalLongitudeDegree)))
                                .altitude(new GroundStation.Altitude(altitudeMeter))
                                .build(),
                        new ElevationMask(Math.toRadians(elevationMaskDegree)),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(startTimestampOfMeasure), ZoneId.of("UTC")),
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(endTimestampOfMeasure), ZoneId.of("UTC"))
                )
        );
    }

}