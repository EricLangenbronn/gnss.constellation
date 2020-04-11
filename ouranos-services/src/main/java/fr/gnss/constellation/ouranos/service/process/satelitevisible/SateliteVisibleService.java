package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("sateliteVisibleService")
@RequiredArgsConstructor
public class SateliteVisibleService implements ISateliteVisibleService {

	// -------------------- Services --------------------

	private final IConfigurationLogMessageService configurationLogMessageService;
	private final IOrbitsDataService orbitsDataService;
	private final IComputationService computationService;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(VisibleSateliteRequestDto visibleSatBean)
			throws TechnicalException, BusinessException {

		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBP.DRF"));
		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> sateliteForPeriod = this.orbitsDataService.getDatasForPeriod(visibleSatBean.getDateDebut(),
				visibleSatBean.getDateFin(), EphemerideType.igs, OrbitType.sp3);
		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBP.FRF"));

		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBP.DTF"));
		List<SatelliteTimeCoordinate<SphericalCoordinate>> l_satelitesVisible = null;
		l_satelitesVisible = computationService.getSateliteVisibleByPeriod(sateliteForPeriod, visibleSatBean.getRadElevationMask(),
				visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), visibleSatBean.getGeodeticCoordinate());
		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBP.FTF"));

		return l_satelitesVisible;
	}

	@Override
	public List<SatelliteCoordinate<SphericalCoordinate>> getSatelliteVisibleBySatellite(VisibleSateliteRequestDto visibleSatBean)
			throws TechnicalException, BusinessException {

		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBS.DRF"));
		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> sateliteForPeriod = this.orbitsDataService.getDatasForPeriod(visibleSatBean.getDateDebut(),
				visibleSatBean.getDateFin(), EphemerideType.igs, OrbitType.sp3);
		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBS.FRF"));

		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBS.DTF"));
		List<SatelliteCoordinate<SphericalCoordinate>> l_satelitesVisible = null;
		l_satelitesVisible = computationService.getSateliteVisibleBySatellite(sateliteForPeriod, visibleSatBean.getRadElevationMask(),
				visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), visibleSatBean.getGeodeticCoordinate());
		log.debug(configurationLogMessageService.getDefautErrorMessage("SVS.GSVBS.FTF"));

		return l_satelitesVisible;
	}

}
