package fr.gnss.constellation.ouranos.services.dao.impl;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.services.dao.ExecutionDao;

public class ExecutionDaoImpl implements ExecutionDao {

	/**
	 * 
	 * @param gStation
	 * @param station
	 * @param satelite
	 * @return norme, élévation, azimut
	 */

	public SphericalCoordinate processSphericalCoordinate(GeodeticCoordinate gStation, CartesianCoordinate3D station,
			CartesianCoordinate3D satelite) {

		CartesianCoordinate3D enuStationSatelite = CoordinateFunction.transformECEFtoENU(gStation.getLatitude(),
				gStation.getLongitude(), satelite, station);

		double[] angles = new double[3];
		double normeProjectionStaSat = Math
				.sqrt(Math.pow(enuStationSatelite.X(), 2) + Math.pow(enuStationSatelite.Y(), 2));

		angles[0] = normeProjectionStaSat; // Angle d'élévation angles[1] =
		Math.atan(normeProjectionStaSat / enuStationSatelite.Z());

		// Angle azimute angles[2] =
		CoordinateFunction.getAzimut(enuStationSatelite.X(), enuStationSatelite.Y());

		if (angles[1] < 0) {
			angles[0] = -1;
			angles[1] = -1;
			angles[2] = -1;
		}

		return new SphericalCoordinate(angles);
	}

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisbleAll(Sp3FileReader sp3FileParser,
			GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		List<Entry<LocalDateTime, List<Satelite>>> fileSatelite = sp3FileParser.getPositionAndClockRecordAll();
		List<Entry<LocalDateTime, List<Satelite>>> sateliteVisible = new ArrayList<>();
		for (Entry<LocalDateTime, List<Satelite>> e : fileSatelite) {
			List<Satelite> tmpSatVisible = new ArrayList<>();
			for (Satelite p : e.getValue()) {
				SphericalCoordinate sphCoord = this.processSphericalCoordinate(gStation,
						CoordinateFunction.geodeticToCartesian(gStation), p.getPosition());
				if ((sphCoord.getInclination() >= 0.2617) && (sphCoord.getInclination() < (3.1415 / 2))) {
					tmpSatVisible.add(p);
				}
			}
			sateliteVisible.add(new SimpleEntry<LocalDateTime, List<Satelite>>(e.getKey(), tmpSatVisible));
		}

		return sateliteVisible;

	}

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
			GeodeticCoordinate gStation) throws TechnicalException, BusinessException {

		Sp3FileReader sp3FileParser = new Sp3FileReader("");
		List<Entry<LocalDateTime, List<Satelite>>> fileSatelite = sp3FileParser.getPositionAndClockRecord(start, end);
		List<Entry<LocalDateTime, List<Satelite>>> sateliteVisible = new ArrayList<>();
		for (Entry<LocalDateTime, List<Satelite>> e : fileSatelite) {
			List<Satelite> tmpSatVisible = new ArrayList<>();
			for (Satelite p : e.getValue()) {
				SphericalCoordinate sphCoord = this.processSphericalCoordinate(gStation,
						CoordinateFunction.geodeticToCartesian(gStation), p.getPosition());
				if ((sphCoord.getInclination() >= 0.2617) && (sphCoord.getInclination() < (3.1415 / 2))) {
					tmpSatVisible.add(p);
				}
			}
			sateliteVisible.add(new SimpleEntry<LocalDateTime, List<Satelite>>(e.getKey(), tmpSatVisible));
		}

		return sateliteVisible;

	}

	public void afficheSateliteVisible(List<Entry<LocalDateTime, List<Satelite>>> map) {

		for (Entry<LocalDateTime, List<Satelite>> e : map) {
			List<Satelite> lpos = e.getValue();
			System.out.println("Satelite visible heure : " + e.getKey());
			for (Satelite pos : lpos) {
				System.out.println(pos.getVehicleId());
			}
		}
		System.out.println("----------------------------------------------");

	}

}