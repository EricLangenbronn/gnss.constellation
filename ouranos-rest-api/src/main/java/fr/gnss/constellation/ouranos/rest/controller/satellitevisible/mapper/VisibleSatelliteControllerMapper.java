package fr.gnss.constellation.ouranos.rest.controller.satellitevisible.mapper;

import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import fr.gnss.constellation.ouranos.rest.controller.satellitevisible.dto.SatelliteDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper
public interface VisibleSatelliteControllerMapper {

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
