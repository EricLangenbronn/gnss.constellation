package fr.gnss.constellation.ouranos.service.satelitevisible;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;

public class SateliteVisibleService implements ISateliteVisibleService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SateliteVisibleService.class);

	private IOrbitsDataService orbitsDataService;
	private IComputationService computationService;

	@Override
	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getSateliteVisible(GeodeticCoordinate groundStation,
			double elevationMask, LocalDateTime dateDebut, LocalDateTime dateFin)
			throws TechnicalException, BusinessException {
		List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> l_satelitesVisible = null;

		File sp3File = orbitsDataService.isDataForPeriod(dateDebut, dateFin);
		if (sp3File != null) {
			LOGGER.debug("Début du traitement du fichier sp3");

			Sp3FileParser sp3FileParser = new Sp3FileParser(new Sp3File(sp3File));

			l_satelitesVisible = computationService.getSateliteVisiblePeriod(sp3FileParser, elevationMask, dateDebut,
					dateFin, groundStation);
			LOGGER.debug("Fin de traitement du fichier sp3");
		} else {

			String message = "Il n'existe pas de données pour la période séléctionnée. [start=" + dateDebut + ", end="
					+ dateFin + "]";
			LOGGER.info(message);
			throw new BusinessException(message);
		}

		return l_satelitesVisible;
	}

	public void setOrbitsDataService(IOrbitsDataService orbitsDataService) {
		this.orbitsDataService = orbitsDataService;
	}

	public void setComputationService(IComputationService computationService) {
		this.computationService = computationService;
	}

}
