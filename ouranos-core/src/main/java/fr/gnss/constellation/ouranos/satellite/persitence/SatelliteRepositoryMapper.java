package fr.gnss.constellation.ouranos.satellite.persitence;

import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public interface SatelliteRepositoryMapper {

  default GeodeticCoordinate domainGroundStationToGeodeticCoordinate(GroundStation groundStation) {
    return new GeodeticCoordinate(groundStation.getLatitude(), groundStation.getLongitude(), groundStation.getAltitude());
  }


  default List<Satellite> satelliteWithSphericalCoordinateForPeriodToSatelliteDomain(
      List<TimeCoordinateSatellitePosition<SphericalCoordinate>> satellitesWithSphericalCoordinate) {

    List<Satellite> satellites = new ArrayList<>();
    for (TimeCoordinateSatellitePosition<SphericalCoordinate> timesSatelliteWithSphericalCoordinate : satellitesWithSphericalCoordinate) {

      // Initialisation de tout les satellites
      for (String satelliteId : timesSatelliteWithSphericalCoordinate.getSatellites().keySet()) {
        satellites.add(
            Satellite.builder()
                .satelliteId(new Satellite.SatelliteId(satelliteId))
                .positionsByTime(new TreeMap<>())
                .build()
        );
      }

      // Ajout de toutes les positions aux satelites précédement crée
      for (String satelliteId : timesSatelliteWithSphericalCoordinate.getSatellites().keySet()) {

        SatellitePosition<SphericalCoordinate> satellitePosition = timesSatelliteWithSphericalCoordinate.getSatellites().get(satelliteId);

        Satellite satellite = satellites.stream()
            .filter(currentSatellite -> StringUtils.equals(currentSatellite.getSatelliteId().getValue(), satellitePosition.getVehicleId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("SatelliteServiceMapper impossible de trouver le satelite [satelliteId=%s] "
                    , satellitePosition.getVehicleId()))
            );

        satellite.addPositionIfAbsent(timesSatelliteWithSphericalCoordinate.getEpochHeaderRecord()
            , sphericalCoordinateToPositionDomain(satellitePosition.getPosition()));
      }
    }


    return satellites;
  }

  default Satellite.Position sphericalCoordinateToPositionDomain(SphericalCoordinate sphericalCoordinate) {
    return new Satellite.Position(new Satellite.Position.RadialDistance(sphericalCoordinate.getRadialDistance())
        , new Satellite.Position.PolarAngle(sphericalCoordinate.getPolarAngle())
        , new Satellite.Position.AzimuthalAngle(sphericalCoordinate.getAzimuthAngle()));

  }
}
