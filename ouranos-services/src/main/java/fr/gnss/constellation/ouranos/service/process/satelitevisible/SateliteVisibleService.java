package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.computation.IComputationService;
import fr.gnss.constellation.ouranos.service.orbitdata.access.IOrbitsDataService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.domain.VisibleSateliteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SateliteVisibleService implements ISateliteVisibleService {

    // -------------------- Services --------------------

    private final IOrbitsDataService orbitsDataService;
    private final IComputationService computationService;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSatelliteVisibleByPeriod(VisibleSateliteRequest visibleSatBean) {

        log.debug("Début de la récupération des fichiers SP3.");
        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> sateliteForPeriod = this.orbitsDataService.getDatasForPeriod(visibleSatBean.getDateDebut(),
                visibleSatBean.getDateFin(), EphemerideType.igs, OrbitType.sp3);
        log.debug("Fin de la récupération des fichiers SP3.");

        log.debug("Début du traitement du fichier SP3.");
        List<SatelliteTimeCoordinate<SphericalCoordinate>> l_satelitesVisible = null;
        l_satelitesVisible = computationService.getSateliteVisibleByPeriod(sateliteForPeriod, visibleSatBean.getRadElevationMask(),
                visibleSatBean.getDateDebut(), visibleSatBean.getDateFin(), visibleSatBean.getGeodeticCoordinate());
        log.debug("Fin de traitement du fichier SP3.");

        return l_satelitesVisible;
    }

}
