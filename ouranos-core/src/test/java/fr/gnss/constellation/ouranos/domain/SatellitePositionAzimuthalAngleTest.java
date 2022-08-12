package fr.gnss.constellation.ouranos.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
