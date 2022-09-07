package fr.gnss.constellation.ouranos.api.controller.dto;

import java.time.LocalDateTime;
import java.util.Map;


public record SatelliteDto(String satelliteId, Map<LocalDateTime, SatelliteDto.Position> positions) {


    public record Position(Double radialDistance, Double polarAngle, Double azimuthalAngle) {
    }
}
