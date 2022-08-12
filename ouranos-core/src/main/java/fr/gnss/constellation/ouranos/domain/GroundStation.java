package fr.gnss.constellation.ouranos.domain;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroundStation {

    /**
     * latitude : phi : radian (NordSud)
     */
    private Latitude latitude;
    /**
     * longitude : lambda : radian (EstOuest)
     */
    private Longitude longitude;
    /**
     * altitude : h : metre
     */
    private Altitude altitude;


    public static class Latitude {
        private final Double latitude;

        public Latitude(Double latitude) {

            if (latitude == null || (latitude < -(Math.PI / 2.0) || latitude > (Math.PI / 2.0))) {
                throw new IllegalArgumentException(String.format("Latitude erronée, elle doit être comprise entre [%s,%s] radian : [latitude=%s]", -(Math.PI / 2.0), (Math.PI / 2.0), latitude));
            }

            this.latitude = latitude;
        }

        public Double getValue() {
            return latitude;
        }
    }

    public static class Longitude {
        private final Double longitude;

        public Longitude(Double longitude) {

            if (longitude == null || (longitude < -(Math.PI) || longitude > (Math.PI))) {
                throw new IllegalArgumentException(String.format("Longitude erronée, elle doit être comprise entre [%s,%s] radian : [longitude=%s]", -Math.PI, Math.PI, longitude));
            }

            this.longitude = longitude;
        }

        public Double getValue() {
            return longitude;
        }
    }

    public static class Altitude {
        private final Double altitude;

        public Altitude(Double altitude) {

            if (altitude != null) {
                if (altitude < -10909.0) {
                    throw new IllegalArgumentException(String.format("Altitude erronée, tu n'es pas un passe muraille : [altitude=%s] metre", altitude));
                }
                if (altitude > 8848.0) {
                    throw new IllegalArgumentException(String.format("Altitude erronée, tu ne touches plus le sol : [altitude=%s] metre", altitude));
                }
            }

            this.altitude = altitude;
        }

        public Double getValue() {
            return altitude;
        }
    }
}
