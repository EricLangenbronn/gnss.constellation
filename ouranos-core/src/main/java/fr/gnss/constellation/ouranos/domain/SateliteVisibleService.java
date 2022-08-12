package fr.gnss.constellation.ouranos.domain;

import fr.gnss.constellation.ouranos.persistence.orbitdata.IOrbitsDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SateliteVisibleService implements ISateliteVisibleService {

    // -------------------- Services --------------------

    private final IOrbitsDataService orbitsDataService;
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
