package fr.gnss.constellation.ouranos.satellite.visible.config;

import fr.gnss.constellation.ouranos.domain.satellite.ElevationMask;
import fr.gnss.constellation.ouranos.domain.satellite.ISatelliteRepository;
import fr.gnss.constellation.ouranos.domain.satellite.visible.SatelliteVisibleService;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Objects;

@ApplicationScoped
@AllArgsConstructor
public class SatelliteVisibleConfiguration {

    private final ISatelliteRepository satelliteRepository;

    private final SatelliteVisibleProperties satelliteVisibleProperties;

    @Produces
    public SatelliteVisibleService getSatelliteVisibleService() {
        return new SatelliteVisibleService(satelliteRepository, new ElevationMask(Math.toRadians(Objects.requireNonNullElse(satelliteVisibleProperties.elevationMaskDegree, 15.0))));
    }
}
