package fr.gnss.constellation.ouranos.domain;

import fr.gnss.constellation.ouranos.domain.satellite.GroundStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GroundStationLongitudeTest {

    @Test
    void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Longitude(null));
    }

    @Test
    void lessThanMinValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Longitude(Math.toRadians(-180.1)));
    }

    @Test
    void minValue() {
        new GroundStation.Longitude(Math.toRadians(-180.0));
    }

    @Test
    void maxValue() {
        new GroundStation.Longitude(Math.toRadians(180.0));
    }

    @Test
    void moreThanMaxValue() {
        assertThrows(IllegalArgumentException.class, () -> new GroundStation.Longitude(Math.toRadians(180.1)));
    }
}
