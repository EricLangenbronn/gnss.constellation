package fr.gnss.constellation.ouranos.api.controller.mapper;

import fr.gnss.constellation.ouranos.api.controller.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper
public interface ResourcesVisibleSatByPeriodControllerMapper {

    /*
    default VisibleSateliteRequest visibleSateliteRequestDtoToDomain(VisibleSatRequestDto source) {

        VisibleSateliteRequest dto = null;

        if (source != null) {

            dto = new VisibleSateliteRequest();
            if (source.getStartDateOfMeasure() != null) {
                Instant start = Instant.ofEpochSecond(source.getStartDateOfMeasure());
                dto.setDateDebut(LocalDateTime.ofInstant(start, ZoneId.of("UTC")));
            }

            if (source.getEndDateOfMeasure() != null) {
                Instant fin = Instant.ofEpochSecond(source.getEndDateOfMeasure());
                dto.setDateFin(LocalDateTime.ofInstant(fin, ZoneId.of("UTC")));
            }

            if (source.getStartDateOfMeasure() != null && source.getEndDateOfMeasure() != null) {
                if (dto.getDateDebut().isAfter(dto.getDateFin())) {
                    throw new IllegalArgumentException("La date de debut doit être avant la date de fin");
                }
            }

            if (source.getElevationMask() != null) {
                Double value = Math.toRadians(source.getElevationMask());
                dto.setRadElevationMask(value);
            }

            if (source.getLatitude() != null && source.getLongitude() != null && source.getAltitude() != null) {
                Double lat = source.getLatitude();
                Double longi = source.getLongitude();
                Double alt = source.getAltitude();

                GeodeticCoordinate geoDTO = new GeodeticCoordinate(lat, longi, alt);
                dto.setGeodeticCoordinate(geoDTO);
            }
        }

        return dto;
    }
    */


    @Mapping(target = "satelliteId", source = "satelliteId", qualifiedByName = "visibleSateliteSatelliteIdDomainToDto")
    @Mapping(target = "positions", source = "positions")
    SatelliteDto visibleSateliteDomainToDto(Satellite satellite);

    List<SatelliteDto> visiblesSatelitesDomainToDto(List<Satellite> satellite);

    @Mapping(target = "radialDistance", source = "radialDistance.value")
    @Mapping(target = "polarAngle", source = "polarAngle.value")
    @Mapping(target = "azimuthalAngle", source = "azimuthalAngle.value")
    SatelliteDto.Position visibleSatelitePositionDomainToDto(Satellite.Position position);

    @Named("visibleSateliteSatelliteIdDomainToDto")
    default String visibleSateliteSatelliteIdDomainToDto(Satellite.SatelliteId satelliteId) {
        return satelliteId.getValue();
    }
}
