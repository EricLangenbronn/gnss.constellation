package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public class SatellitePosition<T extends ICoordinate> {

  private final String vehicleId;

  private final T position;

  /**
   * Initializes a newly created Satelite object.
   *
   * @param vehicleId - Id of the satelite
   * @param position  - T position
   */
  public SatellitePosition(String vehicleId, T position) {
    this.vehicleId = vehicleId;
    this.position = position; // new CartesianCoordinate3D(p_xCoordinate,
    // p_yCoordinate, p_zCoordinate);
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
  public T getPosition() {
    return position;
  }

  @Override
  public String toString() {
    return "Sp3SateliteInformation [vehicleId=" + vehicleId + ", position=" + position + "]";
  }

}
