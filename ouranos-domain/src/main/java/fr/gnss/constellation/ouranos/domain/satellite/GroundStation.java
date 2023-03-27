package fr.gnss.constellation.ouranos.domain.satellite;


import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class GroundStation {

  @NonNull
  private GeodeticCoordinate geodeticCoordinate;

  public double getLatitude() {
    return this.geodeticCoordinate.getLatitude();
  }

  public double getLongitude() {
    return this.geodeticCoordinate.getLongitude();
  }

  public double getAltitude() {
    return this.geodeticCoordinate.getAltitude();
  }
}
