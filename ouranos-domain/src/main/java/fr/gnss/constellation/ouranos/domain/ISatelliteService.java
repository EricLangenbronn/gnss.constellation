package fr.gnss.constellation.ouranos.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ISatelliteService {


    List<Satellite> getSatellitePosition(GroundStation groundStation, LocalDateTime start, LocalDateTime end);
}
