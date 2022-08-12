package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public class SatellitePosition<T extends ICoordinate> {

    private String vehicleId;

    private T position;

    /**
     * Initializes a newly created Satelite object.
     *
     * @param p_vehicleId - Id of the satelite
     * @param position    - T position
     */
    public SatellitePosition(String p_vehicleId, T position) {
        vehicleId = p_vehicleId;
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
