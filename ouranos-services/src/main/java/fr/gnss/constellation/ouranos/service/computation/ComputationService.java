package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
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
	public SphericalCoordinate processSphericalCoordinate(GeodeticCoordinate gStation, CartesianCoordinate3D station,
			CartesianCoordinate3D satelite) {

		CartesianCoordinate3D stationSatelite = CartesianCoordinate3D.minus(station, satelite);

		CartesianCoordinate3D enuStationSatelite = CoordinateFunction.transformECEFtoENU(gStation, stationSatelite);

		double[] angles = new double[3];
		double normeProjectionStaSat = Math
				.sqrt(Math.pow(enuStationSatelite.X(), 2) + Math.pow(enuStationSatelite.Y(), 2));

		angles[0] = normeProjectionStaSat;
		// Angle d'élévation
		angles[1] = Math.atan(normeProjectionStaSat / enuStationSatelite.Z());

		// Angle azimute
		angles[2] = CoordinateFunction.getAzimut(enuStationSatelite.X(), enuStationSatelite.Y());

		if (angles[1] < 0) {
			angles[0] = -1;
			angles[1] = -1;
			angles[2] = -1;
		}

		return new SphericalCoordinate(angles);
	}

	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getSateliteVisibleAll(Sp3FileParser sp3FileParser,
			double elevationMask, GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> fileSatelite = sp3FileParser
				.getPositionAndClockRecordAll();
		List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> sateliteVisible = new ArrayList<>();
		for (Entry<LocalDateTime, List<Sp3SateliteInformation>> e : fileSatelite) {
			List<Sp3SateliteInformation> tmpSatVisible = new ArrayList<>();
			for (Sp3SateliteInformation p : e.getValue()) {
				SphericalCoordinate sphCoord = this.processSphericalCoordinate(gStation,
						CoordinateFunction.geodeticToCartesianWSG84(gStation), p.getPosition());
				if (sphCoord.getAzimuth() != -1) {
					// 3.1415 / 2 rad = 90.0°
					if ((sphCoord.getInclination() >= elevationMask) && (sphCoord.getInclination() < (3.1415 / 2))) {
						tmpSatVisible.add(p);
					}
				}
			}
			sateliteVisible
					.add(new SimpleEntry<LocalDateTime, List<Sp3SateliteInformation>>(e.getKey(), tmpSatVisible));
		}
		afficheSateliteVisible(sateliteVisible);
		return sateliteVisible;

	}

	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getSateliteVisiblePeriod(
			Sp3FileParser sp3FileParser, double elevationMask, LocalDateTime start, LocalDateTime end,
			GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> fileSatelite = sp3FileParser
				.getPositionAndClockRecord(start, end);
		List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> sateliteVisible = new ArrayList<>();
		for (Entry<LocalDateTime, List<Sp3SateliteInformation>> e : fileSatelite) {
			List<Sp3SateliteInformation> tmpSatVisible = new ArrayList<>();
			for (Sp3SateliteInformation p : e.getValue()) {
				SphericalCoordinate sphCoord = this.processSphericalCoordinate(gStation,
						CoordinateFunction.geodeticToCartesianWSG84(gStation), p.getPosition());

				if (sphCoord.getAzimuth() != -1) {
					// 3.1415 / 2 rad = 90.0°
					if ((sphCoord.getInclination() >= elevationMask) && (sphCoord.getInclination() < (3.1415 / 2))) {
						tmpSatVisible.add(p);
					}
				}
			}
			sateliteVisible
					.add(new SimpleEntry<LocalDateTime, List<Sp3SateliteInformation>>(e.getKey(), tmpSatVisible));
		}
		afficheSateliteVisible(sateliteVisible);
		return sateliteVisible;

	}

	public void afficheSateliteVisible(List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> map) {

		for (Entry<LocalDateTime, List<Sp3SateliteInformation>> e : map) {
			List<Sp3SateliteInformation> lpos = e.getValue();
			System.out.println("Satelite visible heure : " + e.getKey());
			for (Sp3SateliteInformation pos : lpos) {
				System.out.println(pos);
			}
		}
		System.out.println("----------------------------------------------");

	}

}