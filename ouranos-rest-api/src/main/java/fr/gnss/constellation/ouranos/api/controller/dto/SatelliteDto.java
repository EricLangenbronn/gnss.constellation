package fr.gnss.constellation.ouranos.api.controller.dto;

import java.time.LocalDateTime;
import java.util.SortedMap;


public record SatelliteDto(String satelliteId, SortedMap<LocalDateTime, Position> positionsByTime) {


    public record Position(Double radialDistance, Double polarAngle, Double azimuthalAngle) {
    }
}
