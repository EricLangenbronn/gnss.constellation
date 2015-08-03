package fr.gnss.constellation.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3Parser;
import fr.gnss.constellation.librairy.almanach.sp3.PositionAndClockRecord;
import fr.gnss.constellation.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;

public class TraitementPositions {

	/**
	 * 
	 * @param gStation
	 * @param station
	 * @param satelite
	 * @return norme, élévation, azimut
	 */
	public static double[] processElevationAzimut(GeodeticCoordinate gStation,
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

		return angles;
	}

	public static CartesianCoordinate3D getCoordinateToPositionAndClock(
			PositionAndClockRecord pos) {
		return new CartesianCoordinate3D(pos.getxCoordinate(),
				pos.getyCoordinate(), pos.getzCoordinate());
	}

	public static void getSateliteVisble(String sp3FileName,
			GeodeticCoordinate gStation) throws Exception {
		Map<LocalDateTime, List<PositionAndClockRecord>> sateliteVisible = new HashMap<LocalDateTime, List<PositionAndClockRecord>>();
		Sp3FileReader sp3FileParser = new Sp3FileReader(sp3FileName);

		Map<LocalDateTime, List<PositionAndClockRecord>> map = sp3FileParser
				.getPositionAndClockRecord();

		for (LocalDateTime localDateTime : map.keySet()) {
			List<PositionAndClockRecord> lpos = map.get(localDateTime);
			for (PositionAndClockRecord pos : lpos) {
				double[] angles = TraitementPositions.processElevationAzimut(
						gStation, CoordinateFunction
								.geodeticToCartesian(gStation),
						TraitementPositions
								.getCoordinateToPositionAndClock(pos));
				if (angles[1] < (3.1415 / 2)) {
					System.out.println("Satelite visible : "
							+ pos.getVehicleId());
				}
			}
		}
	}
}
