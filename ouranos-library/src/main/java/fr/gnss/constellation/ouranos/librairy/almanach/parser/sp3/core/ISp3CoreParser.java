package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.core;

import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public interface ISp3CoreParser {

	public static int NB_MIN_MEASURE = 15;

	SatelliteTimeCoordinate<CartesianCoordinate3D> getPositionAndClockRecord() throws TechnicalException, BusinessException;

	List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getPeriodOfPosition(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException;
}
