package fr.gnss.constellation.ouranos.domain;

import fr.gnss.constellation.ouranos.domain.satellite.Satellite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SatellitePositionPolarAngleTest {

    @Test
    void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.PolarAngle(null));
    }

    @Test
    void lessThanMinValue() {
        assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.PolarAngle(Math.toRadians(-90.1)));
    }

    @Test
    void minValue() {
        new Satellite.Position.PolarAngle(Math.toRadians(-90.0));
    }

    @Test
    void maxValue() {
        new Satellite.Position.PolarAngle(Math.toRadians(90.0));
    }

    @Test
    void moreThanMaxValue() {
        assertThrows(IllegalArgumentException.class, () -> new Satellite.Position.PolarAngle(Math.toRadians(90.1)));
    }
}
