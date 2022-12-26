package fr.gnss.constellation.ouranos.persistence.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrbitDataRepository {

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> getCartesionPositionsForPeriod(LocalDateTime start
            , LocalDateTime end, EphemerideType ephemerideType, OrbitType orbitType);

}