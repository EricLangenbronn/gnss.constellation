package fr.gnss.constellation.ouranos.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	private boolean processComplet = false;

	@Override
	public void launchExecution() throws TechnicalException, BusinessException {
		if (parameters == null) {
			String message = "Le parametrage est null [parameters=null]";
			throw new BusinessException(message);
		} else {
			resultat = new Resultats();
			configurationService.launchExecution(parameters, resultat);
			processComplet = true;
		}
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
