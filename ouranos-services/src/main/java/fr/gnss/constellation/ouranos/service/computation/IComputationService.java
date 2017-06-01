package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

public interface IComputationService {

	public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSateliteVisibleByPeriod(
			List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException;

	public List<SatelliteCoordinate<SphericalCoordinate>> getSateliteVisibleBySatellite(
			List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException;
}
