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
import fr.gnss.constellation.ouranos.services.bean.Parameters;
import fr.gnss.constellation.ouranos.services.bean.Resultats;
import fr.gnss.constellation.ouranos.services.dao.ExecutionDao;
import fr.gnss.constellation.ouranos.services.dao.Sp3Dao;

public class OuranosConfigurationServiceImpl implements OuranosConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosConfigurationServiceImpl.class);

	private OuranosValidationServiceImpl validationService;
	private ExecutionDao executionDao;

	private Sp3Dao sp3Dao;

	/*
	 * public List<Entry<LocalDateTime, List<Satelite>>>
	 * getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
	 * Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws
	 * TechnicalException, BusinessException {
	 * 
	 * List<Entry<LocalDateTime, List<Satelite>>> visibleSats = new
	 * ArrayList<>(); File sp3FileData =
	 * validationService.isDataForPeriod(start, end); if (sp3FileData != null) {
	 * visibleSats = executionService.getSateliteVisiblePeriod(start, end,
	 * sp3FileParser, gStation); } else { String message =
	 * "Il n'existe pas de données pour la période séléctionnée.";
	 * LOGGER.info(message); } return visibleSats; }
	 */

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

	public void setSp3Dao(Sp3Dao sp3Dao) {
		this.sp3Dao = sp3Dao;
	}
	
	public void setExecutionDao(ExecutionDao executionDao) {
		this.executionDao = executionDao;
	}

	public void setValidationService(OuranosValidationServiceImpl validationService) {
		this.validationService = validationService;
	}
}
