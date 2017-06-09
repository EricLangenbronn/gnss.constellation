package fr.gnss.constellation.ouranos.service.orbitdata;

import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public interface IOrbitsDataService {

	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start,
			LocalDateTime end, EphemerideType ephemerideType, OrbitType orbitType)
			throws TechnicalException, BusinessException;

}