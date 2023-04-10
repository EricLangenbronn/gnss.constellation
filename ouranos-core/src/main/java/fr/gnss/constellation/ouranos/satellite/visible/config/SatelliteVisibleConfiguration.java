package fr.gnss.constellation.ouranos.satellite.visible.config;

import fr.gnss.constellation.ouranos.domain.satellite.ElevationMask;
import fr.gnss.constellation.ouranos.domain.satellite.ISatelliteRepository;
import fr.gnss.constellation.ouranos.domain.satellite.visible.SatelliteVisibleService;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class SatelliteVisibleConfiguration {

  @Produces
  public SatelliteVisibleService getSatelliteVisibleService(
      ISatelliteRepository satelliteRepository, SatelliteVisibleProperties satelliteVisibleProperties) {
    
    return new SatelliteVisibleService(satelliteRepository
        , new ElevationMask(Math.toRadians(Objects.requireNonNullElse(satelliteVisibleProperties.elevationMaskDegree, 15.0))));
  }
}
