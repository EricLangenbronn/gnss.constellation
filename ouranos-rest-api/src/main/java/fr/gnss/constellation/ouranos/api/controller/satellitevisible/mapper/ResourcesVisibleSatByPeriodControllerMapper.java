package fr.gnss.constellation.ouranos.api.controller.satellitevisible.mapper;

import fr.gnss.constellation.ouranos.api.controller.satellitevisible.dto.SatelliteDto;
import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper
public interface ResourcesVisibleSatByPeriodControllerMapper {

    @Mapping(target = "satelliteId", source = "satelliteId", qualifiedByName = "visibleSateliteSatelliteIdDomainToDto")
    @Mapping(target = "positionsByTime", source = "positionsByTime")
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
