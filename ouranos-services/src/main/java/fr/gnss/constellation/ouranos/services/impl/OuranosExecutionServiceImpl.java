package fr.gnss.constellation.ouranos.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
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
	private Parameters parameters;
	private Resultats resultat;

	@Override
	public void launchExecution() throws TechnicalException, BusinessException {
		configurationService.launchExecution(parameters, resultat);
	}

	@Override
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;

	}

	@Override
	public Resultats getResultat() {
		return resultat;
	}

	public void setConfigurationService(OuranosConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}
