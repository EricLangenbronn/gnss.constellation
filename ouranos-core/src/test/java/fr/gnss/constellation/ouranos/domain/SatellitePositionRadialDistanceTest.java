package fr.gnss.constellation.ouranos.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
