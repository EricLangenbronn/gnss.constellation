package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.wrapper.XsdWrapper;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

@Service("sateliteVisibleService")
public class SateliteVisibleService implements ISateliteVisibleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SateliteVisibleService.class);

	@Autowired
	private IOrbitsDataService orbitsDataService;
	
	@Autowired
	private IComputationService computationService;

	@Override
	public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(
			VisibleSateliteRequest p_request) throws TechnicalException, BusinessException {

		VisibleSateliteRequestBean visibleSatBean = XsdWrapper.wrapVisibleSateliteRequest(p_request);

		LOGGER.debug("Début de la récupération des fichiers sp3");
		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> sateliteForPeriod = this.orbitsDataService
				.getDatasForPeriod(visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), EphemerideType.igs,
						OrbitType.sp3);
		LOGGER.debug("Fin de la récupération des fichiers sp3");

		LOGGER.debug("Début du traitement du fichier sp3");
		List<SatelliteTimeCoordinate<SphericalCoordinate>> l_satelitesVisible = null;
		l_satelitesVisible = computationService.getSateliteVisibleByPeriod(sateliteForPeriod,
				visibleSatBean.getRadElevationMask(), visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(),
				visibleSatBean.getGeodeticCoordinate());
		LOGGER.debug("Fin de traitement du fichier sp3");

		return l_satelitesVisible;
	}

	@Override
	public List<SatelliteCoordinate<SphericalCoordinate>> getSatelliteVisibleBySatellite(
			VisibleSateliteRequest p_request) throws TechnicalException, BusinessException {

		VisibleSateliteRequestBean visibleSatBean = XsdWrapper.wrapVisibleSateliteRequest(p_request);

		LOGGER.debug("Début de la récupération des fichiers sp3");
		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> sateliteForPeriod = this.orbitsDataService
				.getDatasForPeriod(visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), EphemerideType.igs,
						OrbitType.sp3);
		LOGGER.debug("Fin de la récupération des fichiers sp3");

		LOGGER.debug("Début du traitement du fichier sp3");
		List<SatelliteCoordinate<SphericalCoordinate>> l_satelitesVisible = null;
		l_satelitesVisible = computationService.getSateliteVisibleBySatellite(sateliteForPeriod,
				visibleSatBean.getRadElevationMask(), visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(),
				visibleSatBean.getGeodeticCoordinate());
		LOGGER.debug("Fin de traitement du fichier sp3");

		return l_satelitesVisible;

	}

}
