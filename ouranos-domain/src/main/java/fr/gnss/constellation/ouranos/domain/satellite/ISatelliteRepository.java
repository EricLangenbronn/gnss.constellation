package fr.gnss.constellation.ouranos.domain.satellite;

import java.time.LocalDateTime;
import java.util.List;

public interface ISatelliteRepository {


    List<Satellite> getSatellitePosition(GroundStation groundStation, LocalDateTime start, LocalDateTime end);
}
