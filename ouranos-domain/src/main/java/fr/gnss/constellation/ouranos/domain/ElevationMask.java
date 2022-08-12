package fr.gnss.constellation.ouranos.domain;


public class ElevationMask {

    private final Double elevationMaskRad;

    public ElevationMask(Double elevationMaskRad) {

        if (elevationMaskRad == null || (elevationMaskRad < 0.0 || elevationMaskRad > (Math.PI / 2.0))) {
            throw new IllegalArgumentException(String.format("ElevationMask erronée, la valeur doit être comprise entre [0.0,%s] radian : [elevationMask=%s]", (Math.PI / 2.0), elevationMaskRad));
        }

        this.elevationMaskRad = elevationMaskRad;
    }

    public Double getValue() {
        return elevationMaskRad;
    }
}
