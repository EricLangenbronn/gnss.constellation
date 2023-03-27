package fr.gnss.constellation.ouranos.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import org.junit.jupiter.api.Test;

class SatellitePositionRadialDistanceTest {

  @Test
  void nullValue() {
    assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.RadialDistance(null));
  }

  @Test
  void lessThanMinValue() {
    assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.RadialDistance(-0.1));
  }

  @Test
  void minValue() {
    new Satellite.Position.RadialDistance(0.0);
  }
}
