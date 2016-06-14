package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;

public class OuranosConfigurationServiceImpl implements OuranosConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosConfigurationServiceImpl.class);

	private OuranosValidationServiceImpl validationService;
	
	@Override
	public File getSp3FileForPeriode(LocalDateTime p_startOfMeasure, LocalDateTime p_endOfMeasure)
			throws TechnicalException, BusinessException {
		return validationService.isDataForPeriod(p_startOfMeasure,
				p_endOfMeasure);
	}

	public void setValidationService(OuranosValidationServiceImpl validationService) {
		this.validationService = validationService;
	}
}
