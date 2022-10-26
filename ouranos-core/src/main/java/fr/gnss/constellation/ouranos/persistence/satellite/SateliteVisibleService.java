package fr.gnss.constellation.ouranos.persistence.satellite;

import fr.gnss.constellation.ouranos.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class SateliteVisibleService implements ISateliteVisibleService {

    // -------------------- Services --------------------

    private final ISatelliteService satelliteService;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<Satellite> getSatelliteVisible(GroundStation groundStation, ElevationMask elevationMask, LocalDateTime start, LocalDateTime end) {
        List<Satellite> satellites = satelliteService.getSatellitePosition(groundStation, start, end);

        return Optional.ofNullable(satellites).stream().flatMap(Collection::stream)
                .map(satellite ->
                        Satellite.builder()
                                .satelliteId(satellite.getSatelliteId())
                                .positions(satellite.positionsVisible(elevationMask.getValue()))
                                .build()
                )
                .collect(Collectors.toList());
    }

}
