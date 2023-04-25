package fr.gnss.constellation.ouranos.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.gnss.constellation.ouranos.api.controller.satellitevisible.VisibleSatelliteController;
import fr.gnss.constellation.ouranos.api.controller.satellitevisible.dto.SatelliteDto;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import java.net.URL;
import java.util.List;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
public class VisibleSatelliteControllerIT {

  @TestHTTPEndpoint(VisibleSatelliteController.class)
  @TestHTTPResource
  URL url;

  @Test
  void itWhenRequestValidThenGetVisibleSatellites() {
    VisibleSatelliteClient visibleSatelliteClient = RestClientBuilder.newBuilder()
        .baseUrl(url)
        .build(VisibleSatelliteClient.class);

    List<SatelliteDto> satellitesVisibles = visibleSatelliteClient.getVisibleSatellite(
        1387666800L, 1387925999L, 38.889139, -77.049, 130.049, 15.0);

    assertNotNull(satellitesVisibles);
  }

}
