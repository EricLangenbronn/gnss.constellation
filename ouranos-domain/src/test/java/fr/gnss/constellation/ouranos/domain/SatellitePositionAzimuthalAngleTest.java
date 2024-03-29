package fr.gnss.constellation.ouranos.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import org.junit.jupiter.api.Test;

class SatellitePositionAzimuthalAngleTest {

  @Test
  void nullValue() {
    assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.AzimuthalAngle(null));
  }

  @Test
  void lessThanMinValue() {
    assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.AzimuthalAngle(Math.toRadians(-180.1)));
  }

  @Test
  void minValue() {
    new Satellite.Position.AzimuthalAngle(Math.toRadians(-180.0));
  }

  @Test
  void maxValue() {
    new Satellite.Position.AzimuthalAngle(Math.toRadians(180.0));
  }

  @Test
  void moreThanMaxValue() {
    assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.AzimuthalAngle(Math.toRadians(180.1)));
  }
}
