package fr.gnss.constellation.ouranos.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.services.bean.Parameters;
import fr.gnss.constellation.ouranos.services.bean.Resultats;

public class OuranosExecutionServiceImpl implements OuranosExecutionService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosExecutionServiceImpl.class);

	private OuranosConfigurationService configurationService;
	private Parameters parameters;

	@Override
	public void launchExecution(Resultats resultat) throws TechnicalException, BusinessException {
		configurationService.launchExecution(parameters, resultat);
	}

	@Override
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;

	}

	public void setConfigurationService(OuranosConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}
