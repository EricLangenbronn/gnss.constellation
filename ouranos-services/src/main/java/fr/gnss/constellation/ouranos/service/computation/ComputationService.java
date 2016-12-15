package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

public class ComputationService implements IComputationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputationService.class);

	/**
	 * 
	 * @param gStation
	 * @param station
	 * @param satelite
	 * @return norme, élévation, azimut
	 */
	public SphericalCoordinate processElevationAzimuth(GeodeticCoordinate gStation, CartesianCoordinate3D station,
			CartesianCoordinate3D satelite) {

		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(satelite, station);
		CartesianCoordinate3D stationSateliteNorm = stationSatelite.normalized();

		CartesianCoordinate3D enuStationSatelite = CoordinateFunction.transformECEFtoENU(gStation, stationSateliteNorm);

		double[] angles = new double[3];
		double normeStaSat = enuStationSatelite.magnitude();

		angles[0] = normeStaSat;
		// Angle d'élévation
		angles[1] = Math.asin(enuStationSatelite.Z() / normeStaSat);

		// Angle azimute
		angles[2] = Math.atan2(enuStationSatelite.X() / normeStaSat, enuStationSatelite.Y() / normeStaSat);

		if (angles[1] < 0) {
			angles[0] = -1;
			angles[1] = -1;
			angles[2] = -1;
		} else {
			System.out.println("angle : " + Arrays.toString(angles));
		}

		return new SphericalCoordinate(angles);
	}

	@Override
	public List<SateliteTimeCoordinate> getSateliteVisiblePeriod(Sp3FileParser sp3FileParser, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException {

		List<SateliteTimeCoordinate> fileSatelite = sp3FileParser.getPositionAndClockRecord(start, end);
		List<SateliteTimeCoordinate> sateliteVisible = new ArrayList<>();
		for (SateliteTimeCoordinate e : fileSatelite) {

			SateliteTimeCoordinate sateliteTimeVisible = new SateliteTimeCoordinate(e.getEpochHeaderRecord());
			for (Sp3SateliteInformation p : e.getSatelites().values()) {
				SphericalCoordinate sphCoord = this.processElevationAzimuth(gStation,
						CoordinateFunction.geodeticToCartesianWSG84(gStation), p.getPosition());

				if (sphCoord.getAzimuth() != -1) {
					// System.out.println("azimuth 2, " + sphCoord);
					// 3.1415 / 2 rad = 90.0°
					if ((sphCoord.getInclination() >= elevationMask) && (sphCoord.getInclination() < (3.1415 / 2))) {
						sateliteTimeVisible.addSatellite(p);
					}
				}
			}
			sateliteVisible.add(sateliteTimeVisible);
		}
		afficheSateliteVisibleCount(sateliteVisible);

		return sateliteVisible;
	}

	public void afficheSateliteVisible(List<SateliteTimeCoordinate> satelitesVisible) {

		for (SateliteTimeCoordinate e : satelitesVisible) {
			Map<String, Sp3SateliteInformation> lpos = e.getSatelites();
			System.out.println("Satelite visible heure : " + e.getEpochHeaderRecord());
			for (Sp3SateliteInformation pos : lpos.values()) {
				System.out.println(pos);
			}
		}
		System.out.println("----------------------------------------------");

	}

	public void afficheSateliteVisibleCount(List<SateliteTimeCoordinate> satelitesVisible) {

		for (SateliteTimeCoordinate e : satelitesVisible) {
			System.out.println("Satelite visible heure : " + e.getEpochHeaderRecord());

			int nbSat = e.getSatelites().values().size();
			System.out.print(" : " + nbSat + "\n");
		}
		System.out.println("----------------------------------------------");

	}

}