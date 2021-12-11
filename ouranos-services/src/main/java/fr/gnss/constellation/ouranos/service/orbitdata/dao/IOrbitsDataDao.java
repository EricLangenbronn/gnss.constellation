package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrbitsDataDao {

    List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(String repertoireSp3, Sp3FileName sp3FileName
            , LocalDateTime start, LocalDateTime end);

}
