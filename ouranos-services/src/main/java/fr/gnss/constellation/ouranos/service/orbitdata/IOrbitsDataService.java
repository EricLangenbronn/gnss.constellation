package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface IOrbitsDataService {

	public File isDataForPeriod(LocalDateTime start, LocalDateTime end) throws TechnicalException, BusinessException;
}
