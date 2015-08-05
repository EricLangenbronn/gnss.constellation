package fr.gnss.constellation.librairy.almanach.sp3;

public class PositionAndClockRecord {

	private String vehicleId;

	private double xCoordinate; // Km
	private double yCoordinate; // Km
	private double zCoordinate; // Km
	private double clock; // microsec
	private int xSdev; // (b**n mm)
	private int ySdev; // (b**n mm)
	private int zSdev; // (b**n mm)
	private int cSdev; // (b**n psec)
	private String clockEventFlag;
	private String clockPredFlag;
	private String maneuverFlag;
	private String orbitPredFlag;

	public PositionAndClockRecord(String p_vehicleId, double p_xCoordinate,
			double p_yCoordinate, double p_zCoordinate) {
		vehicleId = p_vehicleId;
		xCoordinate = p_xCoordinate;
		yCoordinate = p_yCoordinate;
		zCoordinate = p_zCoordinate;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public double getzCoordinate() {
		return zCoordinate;
	}

	@Override
	public String toString() {
		return "PositionAndClockRecord [vehicleId=" + vehicleId
				+ ", xCoordinate=" + xCoordinate + ", yCoordinate="
				+ yCoordinate + ", zCoordinate=" + zCoordinate + "]";
	}
}
