package fr.gnss.constellation.ouranos.librairy.almanach.sp3.parser.core.format.position;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3FormatPositionAndClockTypeC extends AbstractSp3FormatPositionAndClock<CartesianCoordinate3D> {

	@Override
	public SatellitePosition<CartesianCoordinate3D> parsePositionAndClock(String line) {

		if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.charAt(0) != 'P')) {
			String message = "Ligne mal formatée, P attendu [line=" + line + "]";
			throw new RuntimeException(message);
		}

		String vehicleId = line.substring(1, 4);
		double x = Double.parseDouble(line.substring(4, 18).trim());
		double y = Double.parseDouble(line.substring(18, 32).trim());
		double z = Double.parseDouble(line.substring(32, 46).trim());
		double clock = Double.parseDouble(line.substring(46, 60).trim());

		// the position values are in km and have to be converted to m
		CartesianCoordinate3D position = new CartesianCoordinate3D(x * 1000, y * 1000, z * 1000);

		return new SatellitePosition<>(vehicleId, position);
	}

}
