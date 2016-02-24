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
import fr.gnss.constellation.ouranos.dao.ExecutionDao;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.model.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;

public class OuranosExecutionServiceImpl implements OuranosExecutionService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosExecutionServiceImpl.class);

	private OuranosConfigurationService configurationService;
	private ExecutionDao executionDao;
	private Parameters parameters;
	private Resultats resultat;
	private boolean processComplet = false;

	@Override
	public void launchExecution() throws TechnicalException, BusinessException {
		resultat = new Resultats();

		File sp3File = configurationService.getSp3FileForPeriode(parameters.getStartOfMeasure(),
				parameters.getEndOfMeasure());
		if (sp3File != null) {
			List<Entry<LocalDateTime, List<Satelite>>> visibleSats = new ArrayList<>();

			Sp3FileReader sp3FileParser = new Sp3FileReader(sp3File);
			visibleSats = executionDao.getSateliteVisiblePeriod(sp3FileParser, parameters.getStartOfMeasure(),
					parameters.getEndOfMeasure(), parameters.getBaseCoordiante());
			resultat.setVisibleSats(visibleSats);
		} else {

			String message = "Il n'existe pas de données pour la période séléctionnée. [start="
					+ parameters.getStartOfMeasure() + ", end=" + parameters.getEndOfMeasure() + "]";
			LOGGER.info(message);
			resultat.addError(new Error(message));
			throw new BusinessException(message);
		}

		processComplet = true;
	}

	public void setExecutionDao(ExecutionDao executionDao) {
		this.executionDao = executionDao;
	}

	@Override
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public Resultats getResultats() {
		return resultat;
	}

	@Override
	public void setProcessComplet(boolean processComplet) {
		this.processComplet = processComplet;
	}

	@Override
	public boolean isProcessComplet() {
		return processComplet;
	}

	public void setConfigurationService(OuranosConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}
