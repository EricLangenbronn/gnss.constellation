package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;

public interface ISateliteVisibleService {

	List<SatelliteCoordinate<SphericalCoordinate>> getSatelliteVisibleBySatellite(VisibleSateliteRequestBean visibleSatBean)
			throws TechnicalException, BusinessException;

	List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(VisibleSateliteRequestBean visibleSatBean)
			throws TechnicalException, BusinessException;
}
