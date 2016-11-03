package fr.gnss.constellation.ouranos.wrapper;

public class XsdWrapper {

	public static fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate wrapGeodeticCoordindate(
			fr.gnss.constellation.ouranos.xsd.GeodeticCoordinate xsdGeodetic) {

		fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate geodeticCoordinate;

		geodeticCoordinate = new fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate(
				xsdGeodetic.getLatitude(), xsdGeodetic.getLongitude(), xsdGeodetic.getAltitude());

		return geodeticCoordinate;
	}

}
