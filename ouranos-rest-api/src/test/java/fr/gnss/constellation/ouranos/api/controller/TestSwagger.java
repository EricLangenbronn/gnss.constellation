package fr.gnss.constellation.ouranos.api.controller;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TestSwagger {

  @Test
  public void testSwaggerAccessible() {
    given()
        .when().get("/q/openapi")
        .then()
        .statusCode(200);
  }
}
