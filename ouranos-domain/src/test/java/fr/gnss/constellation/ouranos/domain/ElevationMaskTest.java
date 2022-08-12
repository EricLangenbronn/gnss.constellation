package fr.gnss.constellation.ouranos.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ElevationMaskTest {

    @Test
    void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> new ElevationMask(null));
    }

    @Test
    void minValue() {
        new ElevationMask(0.0);
    }

    @Test
    void maxValue() {
        new ElevationMask((Math.PI / 2.0));
    }

    @Test
    void otherValue() {
        new ElevationMask(Math.toRadians(15.0));
    }

    @Test
    void lessThanMinValue() {
        assertThrows(IllegalArgumentException.class, () -> new ElevationMask(-0.1));
    }

    @Test
    void moreThanMaxValue() {
        assertThrows(IllegalArgumentException.class, () -> new ElevationMask(15.0));
    }
}
