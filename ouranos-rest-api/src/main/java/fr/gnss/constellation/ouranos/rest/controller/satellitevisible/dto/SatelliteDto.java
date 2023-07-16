package fr.gnss.constellation.ouranos.rest.controller.satellitevisible.dto;

import java.time.LocalDateTime;
import java.util.SortedMap;


public record SatelliteDto(String satelliteId, SortedMap<LocalDateTime, Position> positionsByTime) {

  public record Position(Double radialDistance, Double polarAngle, Double azimuthalAngle) {
  }
}
