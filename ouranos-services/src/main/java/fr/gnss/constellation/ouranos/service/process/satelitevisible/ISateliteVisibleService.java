package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.domain.VisibleSateliteRequest;

import java.util.List;

public interface ISateliteVisibleService {

    List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(VisibleSateliteRequest visibleSatBean);
}
