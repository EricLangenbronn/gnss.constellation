package fr.gnss.constellation.ouranos.service.computation;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

import java.time.LocalDateTime;
import java.util.List;

public interface IComputationService {

    List<SatelliteTimeCoordinate<SphericalCoordinate>> getSateliteVisibleByPeriod(
            List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask
            , LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation);

    List<SatelliteCoordinate<SphericalCoordinate>> getSateliteVisibleBySatellite(
            List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask
            , LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation);
}
