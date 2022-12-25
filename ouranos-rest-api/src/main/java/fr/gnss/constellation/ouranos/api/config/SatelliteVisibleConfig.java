package fr.gnss.constellation.ouranos.api.config;

import fr.gnss.constellation.ouranos.domain.satellite.ISatelliteRepository;
import fr.gnss.constellation.ouranos.domain.satellite.visible.SatelliteVisibleService;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@AllArgsConstructor
public class SatelliteVisibleConfig {

    private final ISatelliteRepository satelliteRepository;

    @Produces
    public SatelliteVisibleService getSatelliteVisibleService() {
        return new SatelliteVisibleService(satelliteRepository);
    }
}
