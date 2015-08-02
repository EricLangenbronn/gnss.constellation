package fr.gnss.constellation.service;

import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;

public class TraitementPositions {

	public double[] process(GeodeticCoordinate gStation,
			CartesianCoordinate station, CartesianCoordinate satelite) {

		CartesianCoordinate enuStationSatelite = CoordinateFunction
				.transformECEFtoENU(gStation.getLatitude(), gStation.getLongitude(), satelite, station);

		double[] angles = new double[3];
		double normeProjectionStaSat = Math.sqrt(Math.pow(
				enuStationSatelite.X(), 2)
				+ Math.pow(enuStationSatelite.Y(), 2));
		angles[0] = normeProjectionStaSat;
		// Angle d'élévation
		angles[1] = Math.atan(normeProjectionStaSat
				/ enuStationSatelite.Z());
		
		// Angle azimute
		angles[2] = CoordinateFunction.getAzimut(
				enuStationSatelite.X(),
				enuStationSatelite.Y());

		return angles;
	}
}
