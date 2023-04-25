package fr.gnss.constellation.ouranos.satellite.persitence;

import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import fr.gnss.constellation.ouranos.domain.satellite.ISatelliteRepository;
import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.coordinate.CoordinateTransformation;
import fr.gnss.constellation.ouranos.orbitdata.service.IOrbitDataService;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class SatelliteRepository implements ISatelliteRepository {

  private final SatelliteRepositoryMapper satelliteRepositoryMapper = Mappers.getMapper(SatelliteRepositoryMapper.class);

  private final IOrbitDataService orbitsDataRepository;


  @Override
  public List<Satellite> getSatellitePosition(GroundStation groundStation, LocalDateTime start, LocalDateTime end) {

    return satelliteRepositoryMapper.satelliteWithSphericalCoordinateForPeriodToSatelliteDomain(
        CoordinateTransformation.transformCartesionsToSphericals(
            orbitsDataRepository.getCartesionPositionsForPeriod(start, end, EphemerideType.igs, OrbitType.sp3)
            , satelliteRepositoryMapper.domainGroundStationToGeodeticCoordinate(groundStation)
        )
    );
  }
}
