package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public interface ISateliteVisibleService {

	public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(
			VisibleSateliteRequest p_request) throws TechnicalException, BusinessException;

	public List<SatelliteCoordinate<SphericalCoordinate>> getSatelliteVisibleBySatellite(
			VisibleSateliteRequest p_request) throws TechnicalException, BusinessException;
}
