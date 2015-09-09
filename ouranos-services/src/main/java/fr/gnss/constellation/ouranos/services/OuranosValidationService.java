package fr.gnss.constellation.ouranos.services;

import java.io.File;
import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface OuranosValidationService {

	public File isDataForPeriod(LocalDateTime start, LocalDateTime end) throws TechnicalException, BusinessException;
}
