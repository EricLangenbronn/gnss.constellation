package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;

public class OuranosConfigurationServiceImpl implements OuranosConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosConfigurationServiceImpl.class);

	private OuranosValidationServiceImpl validationService;
	private OuranosExecutionServiceImpl executionService;

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
			Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Satelite>>> visibleSats = new ArrayList<>();
		File sp3FileData = validationService.isDataForPeriod(start, end);
		if (sp3FileData != null) {
			visibleSats = executionService.getSateliteVisiblePeriod(start, end, sp3FileParser, gStation);
		} else {
			String message = "Il n'existe pas de données pour la période séléctionnée.";
			LOGGER.info(message);
		}
		return visibleSats;
	}

	public void setValidationService(OuranosValidationServiceImpl validationService) {
		this.validationService = validationService;
	}

	public void setExecutionService(OuranosExecutionServiceImpl executionService) {
		this.executionService = executionService;
	}
}
