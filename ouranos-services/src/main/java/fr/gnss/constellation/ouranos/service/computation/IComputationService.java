package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public interface IComputationService {

	public List<SateliteTimeCoordinate> getSateliteVisiblePeriod(List<SateliteTimeCoordinate> fileSatelite,
			double elevationMask, LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException;
}
