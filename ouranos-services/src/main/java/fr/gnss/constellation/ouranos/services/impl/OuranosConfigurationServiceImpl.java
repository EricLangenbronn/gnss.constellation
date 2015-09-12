package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.dao.ExecutionDao;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.model.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;

public class OuranosConfigurationServiceImpl implements OuranosConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosConfigurationServiceImpl.class);

	private OuranosValidationServiceImpl validationService;
	private ExecutionDao executionDao;

	@Override
	public void launchExecution(Parameters parameters, Resultats resultat)
			throws TechnicalException, BusinessException {
		List<Entry<LocalDateTime, List<Satelite>>> visibleSats = new ArrayList<>();
		File sp3FileData = validationService.isDataForPeriod(parameters.getStartOfMeasure(),
				parameters.getTimeOfMeasure());
		if (sp3FileData != null) {
			visibleSats = executionDao.getSateliteVisiblePeriod(parameters.getStartOfMeasure(),
					parameters.getTimeOfMeasure(), parameters.getBaseCoordiante());
			resultat.setVisibleSats(visibleSats);
		} else {
			String message = "Il n'existe pas de données pour la période séléctionnée.";
			LOGGER.info(message);
		}
	}

	public void setExecutionDao(ExecutionDao executionDao) {
		this.executionDao = executionDao;
	}

	public void setValidationService(OuranosValidationServiceImpl validationService) {
		this.validationService = validationService;
	}
}
