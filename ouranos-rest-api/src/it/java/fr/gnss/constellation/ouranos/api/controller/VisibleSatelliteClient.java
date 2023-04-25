package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.satellitevisible.dto.SatelliteDto;
import jakarta.ws.rs.GET;
import java.util.List;


public interface VisibleSatelliteClient {

  @GET
  List<SatelliteDto> getVisibleSatellite(Long startTimestampOfMeasure, Long endTimestampOfMeasure, Double decimalLatitudeDegree
      , Double decimalLongitudeDegree, Double altitudeMeter, Double elevationMaskDegree);
}
