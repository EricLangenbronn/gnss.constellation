package fr.gnss.constellation.ouranos.domain.satellite.visible;

import fr.gnss.constellation.ouranos.domain.satellite.ElevationMask;
import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import fr.gnss.constellation.ouranos.domain.satellite.ISatelliteRepository;
import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SatelliteVisibleService {

  // -------------------- Services --------------------

  private final ISatelliteRepository satelliteRepository;

  private final ElevationMask defaultElevationMask;

  // ------------------------ Constructeur(s) ------------------------

  public SatelliteVisibleService(ISatelliteRepository satelliteService, ElevationMask defaultElevationMask) {
    if (defaultElevationMask == null) {
      throw new IllegalArgumentException("La valeur par défaut de l''élevation mask ne peut être null");
    }

    this.defaultElevationMask = defaultElevationMask;
    this.satelliteRepository = satelliteService;
  }

  // -------------------- Methodes de l'interface --------------------

  public List<Satellite> getSatelliteVisible(GroundStation groundStation, ElevationMask elevationMask
      , LocalDateTime start, LocalDateTime end) {

    List<Satellite> satellites = satelliteRepository.getSatellitePosition(groundStation, start, end);

    ElevationMask currentElevationMask = elevationMask == null ? defaultElevationMask : elevationMask;
    if (elevationMask != currentElevationMask) {
      log.warn("On utilise la configuration par défaut pour l''élévation mask, il se pourrait que ce ne soit pas voulu");
    }

    return Optional.ofNullable(satellites).stream().flatMap(Collection::stream)
        .map(satellite -> {
              Satellite satelliteVisible = Satellite.builder()
                  .satelliteId(satellite.getSatelliteId())
                  .positionsByTime(satellite.positionsVisible(currentElevationMask.getValue()))
                  .build();

              if (satelliteVisible.getPositionsByTime().size() > 0) {
                return satelliteVisible;
              } else {
                return null;
              }
            }
        )
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

}
