package fr.gnss.constellation.ouranos.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GroundStationLatitudeTest {

    @Test
    void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Latitude(null));
    }

    @Test
    void lessThanMinValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Latitude(Math.toRadians(-90.1)));
    }

    @Test
    void minValue() {
        new GroundStation.Latitude(Math.toRadians(-90.0));
    }

    @Test
    void maxValue() {
        new GroundStation.Latitude(Math.toRadians(90.0));
    }

    @Test
    void moreThanMaxValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Latitude(Math.toRadians(90.1)));
    }
}
