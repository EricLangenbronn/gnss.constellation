package fr.gnss.constellation.ouranos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public interface OuranosConfigurationService {

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
			Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws TechnicalException, BusinessException;
}
