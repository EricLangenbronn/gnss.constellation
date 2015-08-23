package fr.gnss.constellation.librairy.almanach.sp3;

import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate3D;

public class Satelite {

	private String vehicleId;

	private CartesianCoordinate3D position;

	public Satelite(String p_vehicleId, double p_xCoordinate,
			double p_yCoordinate, double p_zCoordinate) {
		vehicleId = p_vehicleId;
		position = new CartesianCoordinate3D(p_xCoordinate, p_yCoordinate, p_zCoordinate);
	}
	
	public String getVehicleId() {
		return vehicleId;
	}

	public CartesianCoordinate3D getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "PositionAndClockRecord [vehicleId=" + vehicleId + ", position=" + position + "]";
	}

}
