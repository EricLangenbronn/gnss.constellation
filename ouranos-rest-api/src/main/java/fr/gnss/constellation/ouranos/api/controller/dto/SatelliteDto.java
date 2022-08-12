package fr.gnss.constellation.ouranos.api.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class SatelliteDto {

    private String satelliteId;
    private Map<LocalDateTime, SatelliteDto.Position> positions;

    @Data
    public static class Position {
        private final Double radialDistance;
        private final Double polarAngle;
        private final Double azimuthalAngle;
    }


}
