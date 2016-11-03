package fr.gnss.constellation.ouranos.services;

import java.io.File;
import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface IConfigurationService {

	public File getSp3FileForPeriode(LocalDateTime p_startOfMeasure, LocalDateTime p_endOfMeasure)
			throws TechnicalException, BusinessException;
}
