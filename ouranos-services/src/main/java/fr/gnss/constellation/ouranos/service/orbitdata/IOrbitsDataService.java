package fr.gnss.constellation.ouranos.service.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrbitsDataService {

    List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start
            , LocalDateTime end, EphemerideType ephemerideType, OrbitType orbitType);

}