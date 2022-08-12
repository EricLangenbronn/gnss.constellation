package fr.gnss.constellation.ouranos.persistence.satellite;

import fr.gnss.constellation.ouranos.domain.GroundStation;
import fr.gnss.constellation.ouranos.domain.Satellite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface SatelliteServiceMapper {

    default GeodeticCoordinate domainGroundStationToGeodeticCoordinate(GroundStation groundStation) {
        return new GeodeticCoordinate(groundStation.getLatitude().getValue()
                , groundStation.getLongitude().getValue(), groundStation.getAltitude().getValue());
    }


    default List<Satellite> satelliteWithSphericalCoordinateForPeriodToSatelliteDomain(List<TimeCoordinateSatellitePosition<SphericalCoordinate>> satellitesWithSphericalCoordinate) {

        List<Satellite> satellites = new ArrayList<>();
        for (TimeCoordinateSatellitePosition<SphericalCoordinate> timesSatelliteWithSphericalCoordinate : satellitesWithSphericalCoordinate) {

            // Initialisation de tout les satellites
            for (String satelliteId : timesSatelliteWithSphericalCoordinate.getSatellites().keySet()) {
                satellites.add(
                        Satellite.builder()
                                .satelliteId(new Satellite.SatelliteId(satelliteId))
                                .positions(new HashMap<>())
                                .build()
                );
            }

            // Ajout de toutes les positions aux satelites précédement crée
            for (String satelliteId : timesSatelliteWithSphericalCoordinate.getSatellites().keySet()) {

                SatellitePosition<SphericalCoordinate> satellitePosition = timesSatelliteWithSphericalCoordinate.getSatellites().get(satelliteId);

                Satellite satellite = satellites.stream()
                        .filter(currentSatellite -> StringUtils.equals(currentSatellite.getSatelliteId().getValue(), satellitePosition.getVehicleId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(String.format("SatelliteServiceMapper impossible de trouver le satelite [satelliteId=%s] ", satellitePosition.getVehicleId())));
                satellite.addPositionIfAbsent(timesSatelliteWithSphericalCoordinate.getEpochHeaderRecord(), sphericalCoordinateToPositionDomain(satellitePosition.getPosition()));
            }
        }


        return satellites;
    }

    default Satellite.Position sphericalCoordinateToPositionDomain(SphericalCoordinate sphericalCoordinate) {
        return new Satellite.Position(new Satellite.Position.RadialDistance(sphericalCoordinate.getRadiusDistance()),
                new Satellite.Position.PolarAngle(sphericalCoordinate.getInclination()), new Satellite.Position.AzimuthalAngle(sphericalCoordinate.getAzimuth()));

    }
}
