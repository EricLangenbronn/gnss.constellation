package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3SateliteInformation {

	private String vehicleId;

	private CartesianCoordinate3D position;

	/**
	 * Initializes a newly created Satelite object.
	 * 
	 * @param p_vehicleId
	 *            - Id of the satelite
	 * @param p_xCoordinate
	 *            - x coordinate
	 * @param p_yCoordinate
	 *            - y coordinate
	 * @param p_zCoordinate
	 *            - z coordinate
	 */
	public Sp3SateliteInformation(String p_vehicleId, double p_xCoordinate, double p_yCoordinate, double p_zCoordinate) {
		vehicleId = p_vehicleId;
		position = new CartesianCoordinate3D(p_xCoordinate, p_yCoordinate, p_zCoordinate);
	}

	/**
	 * Get the id of the satelite.
	 * 
	 * @return the id of the satelite
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * Get the position of the satelite.
	 * 
	 * @return the position of the satelite
	 */
	public CartesianCoordinate3D getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Sp3SateliteInformation [vehicleId=" + vehicleId + ", position=" + position + "]";
	}

}
