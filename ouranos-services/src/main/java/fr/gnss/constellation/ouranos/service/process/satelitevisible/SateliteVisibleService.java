package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.wrapper.XsdWrapper;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class SateliteVisibleService implements ISateliteVisibleService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SateliteVisibleService.class);

	private IOrbitsDataService orbitsDataService;
	private IComputationService computationService;

	@Override
	public List<SateliteTimeCoordinate> getSateliteVisible(VisibleSateliteRequest p_request)
			throws TechnicalException, BusinessException {

		VisibleSateliteRequestBean visibleSatBean = XsdWrapper.wrapVisibleSateliteRequest(p_request);

		List<SateliteTimeCoordinate> l_satelitesVisible = null;

		LOGGER.debug("Début de la récupération des fichiers sp3");
		List<SateliteTimeCoordinate> sateliteForPeriod = this.orbitsDataService.getDatasForPeriod(
				visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), EphemerideType.igs, OrbitType.sp3);
		LOGGER.debug("Fin de la récupération des fichiers sp3");

		LOGGER.debug("Début du traitement du fichier sp3");
		l_satelitesVisible = computationService.getSateliteVisiblePeriod(sateliteForPeriod,
				visibleSatBean.getRadElevationMask(), visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(),
				visibleSatBean.getGeodeticCoordinate());
		LOGGER.debug("Fin de traitement du fichier sp3");

		return l_satelitesVisible;
	}

	public void setOrbitsDataService(IOrbitsDataService orbitsDataService) {
		this.orbitsDataService = orbitsDataService;
	}

	public void setComputationService(IComputationService computationService) {
		this.computationService = computationService;
	}

}
