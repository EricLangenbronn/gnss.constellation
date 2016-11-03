package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.IConfigurationService;

public class ConfigurationService implements IConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

	private ValidationService validationService;

	@Override
	public File getSp3FileForPeriode(LocalDateTime p_startOfMeasure, LocalDateTime p_endOfMeasure)
			throws TechnicalException, BusinessException {
		return validationService.isDataForPeriod(p_startOfMeasure, p_endOfMeasure);
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}
}
