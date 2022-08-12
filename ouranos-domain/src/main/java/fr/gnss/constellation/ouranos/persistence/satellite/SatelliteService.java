package fr.gnss.constellation.ouranos.persistence.satellite;

import fr.gnss.constellation.ouranos.domain.GroundStation;
import fr.gnss.constellation.ouranos.domain.ISatelliteService;
import fr.gnss.constellation.ouranos.domain.Satellite;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticTransformation;
import fr.gnss.constellation.ouranos.persistence.orbitdata.IOrbitsDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SatelliteService implements ISatelliteService {

    private final SatelliteServiceMapper satelliteServiceMapper = Mappers.getMapper(SatelliteServiceMapper.class);

    private final IOrbitsDataService orbitsDataService;


    @Override
    public List<Satellite> getSatellitePosition(GroundStation groundStation, LocalDateTime start, LocalDateTime end) {

        return satelliteServiceMapper.satelliteWithSphericalCoordinateForPeriodToSatelliteDomain(
                GeodeticTransformation.transformCartesionsToSphericals(
                        orbitsDataService.getCartesionPositionsForPeriod(start, end, EphemerideType.igs, OrbitType.sp3),
                        satelliteServiceMapper.domainGroundStationToGeodeticCoordinate(groundStation)
                )
        );
    }
}
