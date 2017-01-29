package fr.gnss.constellation.ouranos.wrapper;

public class XsdWrapper {

	public static fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate wrapGeodeticCoordindate(
			fr.gnss.constellation.ouranos.xsd.request.GeodeticCoordinate xsdGeodetic) {

		fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate geodeticCoordinate;

		geodeticCoordinate = new fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate(
				Math.toRadians(xsdGeodetic.getLatitude()), Math.toRadians(xsdGeodetic.getLongitude()),xsdGeodetic.getAltitude());

		return geodeticCoordinate;
	}

}
