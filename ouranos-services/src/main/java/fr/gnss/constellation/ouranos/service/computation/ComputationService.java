package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticTransformation;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

public class ComputationService implements IComputationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputationService.class);

	@Override
	public List<SatelliteTimeCoordinate<SphericalCoordinate>> getSateliteVisibleByPeriod(
			List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<SphericalCoordinate>> sateliteVisibleByPeriod = new ArrayList<>();
		for (SatelliteTimeCoordinate<CartesianCoordinate3D> e : visibleSatellite) {

			SatelliteTimeCoordinate<SphericalCoordinate> sateliteTimeVisible = new SatelliteTimeCoordinate<>(
					e.getEpochHeaderRecord());
			for (SatellitePosition<CartesianCoordinate3D> p : e.getSatellites().values()) {
				double[] sphCoord = GeodeticTransformation.processElevationAzimuth(gStation, p.getPosition());
				SphericalCoordinate spheCoord = new SphericalCoordinate(sphCoord[0], sphCoord[2], sphCoord[1]);
				SatellitePosition<SphericalCoordinate> sp3SateliteInformation = new SatellitePosition<>(
						p.getVehicleId(), spheCoord);

				// sphCoord[2] = azimuth, azimuth doit être positif sinon c'est
				// que le satelite est pas du bon coté de la terre
				if (sphCoord[2] >= 0) {
					// 3.1415 / 2 rad = 90.0°, on vérifie qu'il est entre 90° et
					// l'élévation
					if ((sphCoord[1] >= elevationMask) && (sphCoord[1] < (3.141592 / 2))) {
						sateliteTimeVisible.addSatellite(sp3SateliteInformation.getVehicleId(), sp3SateliteInformation);
					}
				}
			}
			sateliteVisibleByPeriod.add(sateliteTimeVisible);
		}

		return sateliteVisibleByPeriod;
	}

	@Override
	public List<SatelliteCoordinate<SphericalCoordinate>> getSateliteVisibleBySatellite(
			List<SatelliteTimeCoordinate<CartesianCoordinate3D>> visibleSatellite, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException {

		Map<String, SatelliteCoordinate<SphericalCoordinate>> sateliteVisible = new HashMap<>();
		for (SatelliteTimeCoordinate<CartesianCoordinate3D> e : visibleSatellite) {

			SatelliteTimeCoordinate<SphericalCoordinate> sateliteTimeVisible = new SatelliteTimeCoordinate<>(
					e.getEpochHeaderRecord());
			for (SatellitePosition<CartesianCoordinate3D> p : e.getSatellites().values()) {

				double[] sphCoord = GeodeticTransformation.processElevationAzimuth(gStation, p.getPosition());
				SphericalCoordinate spheCoord = new SphericalCoordinate(sphCoord[0], sphCoord[2], sphCoord[1]);
				SatellitePosition<SphericalCoordinate> satellitePosition = new SatellitePosition<>(
						p.getVehicleId(), spheCoord);

				// sphCoord[2] = azimuth, azimuth doit être positif sinon c'est
				// que le satelite est pas du bon coté de la terre
				if (sphCoord[2] >= 0) {
					// 3.1415 / 2 rad = 90.0°, on vérifie qu'il est entre 90° et
					// l'élévation
					if ((sphCoord[1] >= elevationMask) && (sphCoord[1] < (3.141592 / 2))) {
						if (sateliteVisible.containsKey(satellitePosition.getVehicleId())) {
							SatelliteCoordinate<SphericalCoordinate> satellite = sateliteVisible.get(satellitePosition.getVehicleId());
							satellite.addSatellite(e.getEpochHeaderRecord(), satellitePosition);
						} else {
							SatelliteCoordinate<SphericalCoordinate> satelliteCoord = new SatelliteCoordinate<>(satellitePosition.getVehicleId());
							satelliteCoord.addSatellite(e.getEpochHeaderRecord(), satellitePosition);
							sateliteVisible.put(satellitePosition.getVehicleId(), satelliteCoord);
						}
					}
				}
			}
		}

		return new ArrayList<SatelliteCoordinate<SphericalCoordinate>>(sateliteVisible.values());
	}

}