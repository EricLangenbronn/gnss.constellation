package fr.gnss.constellation.ouranos.rest.controller;

import fr.gnss.constellation.ouranos.rest.controller.satellitevisible.dto.SatelliteDto;
import jakarta.ws.rs.GET;
import java.util.List;


public interface VisibleSatelliteClient {

  @GET
  List<SatelliteDto> getVisibleSatellite(Long startTimestampOfMeasure, Long endTimestampOfMeasure, Double decimalLatitudeDegree
      , Double decimalLongitudeDegree, Double altitudeMeter, Double elevationMaskDegree);
}
