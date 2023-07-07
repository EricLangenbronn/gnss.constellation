package fr.gnss.constellation.ouranos.api.controller.satellitevisible;


import fr.gnss.constellation.ouranos.api.controller.satellitevisible.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.api.controller.satellitevisible.mapper.VisibleSatelliteControllerMapper;
import fr.gnss.constellation.ouranos.domain.satellite.ElevationMask;
import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import fr.gnss.constellation.ouranos.domain.satellite.visible.SatelliteVisibleService;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@Path("/api/visibleSat")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@RequiredArgsConstructor
public class VisibleSatelliteController {

  private final VisibleSatelliteControllerMapper visibleSatelliteControllerMapper =
      Mappers.getMapper(VisibleSatelliteControllerMapper.class);
  private final SatelliteVisibleService sateliteVisibleService;

  @GET
  @Path("")
  @Timed(value = "visible-satellite-timer", description = "A measure how long it takes to perform the visible satelites.")
  @Counted(value = "visible-satellite-counter", description = "A measure how many times is perform the visible satelites.")
  public List<SatelliteDto> getVisibleSatellite(
      @NotNull @QueryParam("startDateOfMeasure") Long startTimestampOfMeasure
      , @NotNull @QueryParam("endDateOfMeasure") Long endTimestampOfMeasure
      , @NotNull @QueryParam("decimalLatitudeDegree") Double decimalLatitudeDegree
      , @NotNull @QueryParam("decimalLongitudeDegree") Double decimalLongitudeDegree
      , @NotNull @QueryParam("altitude") Double altitudeMeter
      , @NotNull @QueryParam("elevationMask") Double elevationMaskDegree) {

    return visibleSatelliteControllerMapper.visiblesSatelitesDomainToDto(
        sateliteVisibleService.getSatelliteVisible(
            GroundStation.builder()
                .geodeticCoordinate(
                    new GeodeticCoordinate(
                        Math.toRadians(decimalLatitudeDegree)
                        , Math.toRadians(decimalLongitudeDegree)
                        , altitudeMeter)
                )
                .build()
            , new ElevationMask(Math.toRadians(elevationMaskDegree))
            , LocalDateTime.ofInstant(Instant.ofEpochSecond(startTimestampOfMeasure), ZoneId.of("UTC"))
            , LocalDateTime.ofInstant(Instant.ofEpochSecond(endTimestampOfMeasure), ZoneId.of("UTC"))
        )
    );
  }

}