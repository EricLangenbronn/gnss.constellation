package fr.gnss.constellation.ouranos.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ISateliteVisibleService {

    List<Satellite> getSatelliteVisible(GroundStation groundStation, ElevationMask elevationMask, LocalDateTime start, LocalDateTime end);
}
