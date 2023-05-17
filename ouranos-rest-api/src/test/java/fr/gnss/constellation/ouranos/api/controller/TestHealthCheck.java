package fr.gnss.constellation.ouranos.api.controller;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Disabled
// TODO correct it : java.lang.RuntimeException: java.util.ServiceConfigurationError: org.eclipse.microprofile.config.spi.ConfigProviderResolver: io.smallrye.config.SmallRyeConfigProviderResolver not a subtype
public class TestHealthCheck {

  @Test
  public void testHealthCheckAccessible() {
    given()
        .when().get("/q/health/")
        .then()
        .statusCode(200);
  }
}
