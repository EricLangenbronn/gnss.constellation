package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public interface IOrbitsDataDao {

	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(String repertoireSp3, Sp3FileName sp3FileName,
			LocalDateTime start, LocalDateTime end) throws TechnicalException, BusinessException;

}
