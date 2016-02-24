package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
