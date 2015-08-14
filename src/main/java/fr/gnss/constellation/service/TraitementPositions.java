package fr.gnss.constellation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import com.sun.corba.se.spi.orbutil.fsm.State;

import javafx.geometry.Pos;
import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3Parser;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;
import fr.gnss.constellation.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.librairy.coordinate.SphericalCoordinate;

public class TraitementPositions {

	/**
	 * 
	 * @param gStation
	 * @param station
	 * @param satelite
	 * @return norme, élévation, azimut
	 */
	public static SphericalCoordinate processSphericalCoordinate(GeodeticCoordinate gStation,
			CartesianCoordinate3D station, CartesianCoordinate3D satelite) {

		CartesianCoordinate3D enuStationSatelite = CoordinateFunction
				.transformECEFtoENU(gStation.getLatitude(),
						gStation.getLongitude(), satelite, station);

		double[] angles = new double[3];
		double normeProjectionStaSat = Math.sqrt(Math.pow(
				enuStationSatelite.X(), 2)
				+ Math.pow(enuStationSatelite.Y(), 2));

		angles[0] = normeProjectionStaSat;
		// Angle d'élévation
		angles[1] = Math.atan(normeProjectionStaSat / enuStationSatelite.Z());

		// Angle azimute
		angles[2] = CoordinateFunction.getAzimut(enuStationSatelite.X(),
				enuStationSatelite.Y());

		if (angles[1] < 0) {
			angles[0] = -1;
			angles[1] = -1;
			angles[2] = -1;
		}

		return new SphericalCoordinate(angles);
	}

	public static CartesianCoordinate3D getCoordinateToPositionAndClock(
			PositionAndClockRecord pos) {
		return new CartesianCoordinate3D(pos.getxCoordinate(),
				pos.getyCoordinate(), pos.getzCoordinate());
	}

	public static List<Entry<LocalDateTime, List<PositionAndClockRecord>>> getSateliteVisble(
			Sp3FileReader sp3FileParser, GeodeticCoordinate gStation) throws Exception {
		
		List<Entry<LocalDateTime, List<PositionAndClockRecord>>> fileSatelite = sp3FileParser.getPositionAndClockRecord();
		List<Entry<LocalDateTime, List<PositionAndClockRecord>>> sateliteVisible = new ArrayList<>();
		for(Entry<LocalDateTime, List<PositionAndClockRecord>> e: fileSatelite) {
			List<PositionAndClockRecord> tmpSatVisible = new ArrayList<>();
			for(PositionAndClockRecord p : e.getValue()) {
				SphericalCoordinate sphCoord = TraitementPositions.processSphericalCoordinate(
						gStation, CoordinateFunction
								.geodeticToCartesian(gStation),
						TraitementPositions
								.getCoordinateToPositionAndClock(p));
				if ((sphCoord.getInclination() >= 0.2617) && (sphCoord.getInclination() < (3.1415 / 2))) {
					tmpSatVisible.add(p);
				}
			}
			sateliteVisible.add(new SimpleEntry<LocalDateTime, List<PositionAndClockRecord>>(e.getKey(), tmpSatVisible));
		}
		
		/*
		Map<LocalDateTime, List<PositionAndClockRecord>> sateliteVisible = new HashMap<LocalDateTime, List<PositionAndClockRecord>>();
		for (LocalDateTime localDateTime : map.keySet()) {
			sateliteVisible.put(localDateTime,
					new ArrayList<PositionAndClockRecord>());

			List<PositionAndClockRecord> lpos = map.get(localDateTime);
			for (PositionAndClockRecord pos : lpos) {
				double[] angles = TraitementPositions.processElevationAzimut(
						gStation, CoordinateFunction
								.geodeticToCartesian(gStation),
						TraitementPositions
								.getCoordinateToPositionAndClock(pos));
				if ((angles[1] >= 0.2617) && (angles[1] < (3.1415 / 2))) {
					sateliteVisible.get(localDateTime).add(pos);
				}
			}
		}
		*/

		return sateliteVisible;

	}

	public static void afficheSateliteVisible(
			List<Entry<LocalDateTime, List<PositionAndClockRecord>>> map) {

		for (Entry<LocalDateTime, List<PositionAndClockRecord>> e: map) {
			List<PositionAndClockRecord> lpos = e.getValue();
			System.out.println("Satelite visible heure : " + e.getKey());
			for (PositionAndClockRecord pos : lpos) {
				System.out.println(pos.getVehicleId());
			}
		}
		System.out.println("----------------------------------------------");

	}
}
