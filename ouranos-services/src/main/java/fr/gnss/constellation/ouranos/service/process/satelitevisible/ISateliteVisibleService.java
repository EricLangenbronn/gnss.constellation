package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;

import java.util.List;

public interface ISateliteVisibleService {

    List<SatelliteCoordinate<SphericalCoordinate>> getSatelliteVisibleBySatellite(VisibleSateliteRequestDto visibleSatBean);

    List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(VisibleSateliteRequestDto visibleSatBean);
}
