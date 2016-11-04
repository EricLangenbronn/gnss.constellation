package fr.gnss.constellation.ouranos.service.satelitevisible;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public interface ISateliteVisibleService {

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisible(GeodeticCoordinate groundStation,
			double elevationMask, LocalDateTime l_dateDebut, LocalDateTime l_dateFin)
			throws TechnicalException, BusinessException;
}
