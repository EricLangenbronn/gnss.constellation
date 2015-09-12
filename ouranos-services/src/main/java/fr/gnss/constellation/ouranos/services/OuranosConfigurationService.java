package fr.gnss.constellation.ouranos.services;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.model.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;

public interface OuranosConfigurationService {

	/*
	 * public List<Entry<LocalDateTime, List<Satelite>>>
	 * getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
	 * Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws
	 * TechnicalException, BusinessException;
	 */

	public void launchExecution(Parameters parameters, Resultats resultat) throws TechnicalException, BusinessException;
}
