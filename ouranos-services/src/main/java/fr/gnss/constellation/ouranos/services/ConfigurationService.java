package fr.gnss.constellation.ouranos.services;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;

public class ConfigurationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(ConfigurationService.class);

	private ValidationService validService;
	private ExecutionService executeService;

	public ConfigurationService() {
		validService = new ValidationService();
		executeService = new ExecutionService();
	}

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
			Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Satelite>>> visibleSats = new ArrayList<>();
		File sp3FileData = validService.isDataForPeriod(start, end);
		if (sp3FileData != null) {
			visibleSats = executeService.getSateliteVisiblePeriod(start, end, sp3FileParser, gStation);
		} else {
			String message = "Il n'existe pas de données pour la période séléctionnée.";
			LOGGER.info(message);
		}
		return visibleSats;
	}
}
