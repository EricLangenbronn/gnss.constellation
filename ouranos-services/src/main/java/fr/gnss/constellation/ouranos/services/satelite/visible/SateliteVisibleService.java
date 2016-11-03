package fr.gnss.constellation.ouranos.services.satelite.visible;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.dao.IExecutionDao;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.services.IConfigurationService;
import fr.gnss.constellation.ouranos.wrapper.XsdWrapper;
import fr.gnss.constellation.ouranos.xsd.VisibleSateliteRequest;

public class SateliteVisibleService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SateliteVisibleService.class);

	private IConfigurationService configurationService;
	private IExecutionDao executionDao;

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisible(VisibleSateliteRequest p_requete)
			throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Satelite>>> l_satelitesVisible = null;

		LocalDateTime l_dateDebut = p_requete.getStartDateOfMeasure().toGregorianCalendar().toZonedDateTime()
				.toLocalDateTime();
		LocalDateTime l_dateFin = p_requete.getEndDateOfMeasure().toGregorianCalendar().toZonedDateTime()
				.toLocalDateTime();
		File sp3File = configurationService.getSp3FileForPeriode(l_dateDebut, l_dateFin);
		if (sp3File != null) {
			LOGGER.debug("Début du traitement du fichier sp3");

			Sp3FileReader sp3FileParser = new Sp3FileReader(sp3File);
			GeodeticCoordinate geodeticCoordinate = XsdWrapper.wrapGeodeticCoordindate(p_requete.getGroundStation());
			l_satelitesVisible = executionDao.getSateliteVisiblePeriod(sp3FileParser, p_requete.getElevationMask(),
					l_dateDebut, l_dateFin, geodeticCoordinate);
			LOGGER.debug("Fin de traitement du fichier sp3");
		} else {

			String message = "Il n'existe pas de données pour la période séléctionnée. [start="
					+ p_requete.getStartDateOfMeasure() + ", end=" + p_requete.getEndDateOfMeasure() + "]";
			LOGGER.info(message);
			throw new BusinessException(message);
		}

		return l_satelitesVisible;
	}

	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setExecutionDao(IExecutionDao executionDao) {
		this.executionDao = executionDao;
	}
}
