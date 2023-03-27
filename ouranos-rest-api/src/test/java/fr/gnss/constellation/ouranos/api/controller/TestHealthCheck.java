package fr.gnss.constellation.ouranos.api.controller;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TestHealthCheck {

  @Test
  public void testHealthCheckAccessible() {
    given()
        .when().get("/q/health/")
        .then()
        .statusCode(200);
  }
}
