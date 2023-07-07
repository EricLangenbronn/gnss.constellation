module ouranos.http {
  requires com.fasterxml.jackson.databind;
  requires io.smallrye.common.constraint;
  requires jakarta.cdi;
  requires jakarta.validation;
  requires jakarta.ws.rs;
  requires java.desktop;
  requires lombok;
  requires micrometer.core;
  requires microprofile.health.api;
  requires org.apache.commons.lang3;
  requires org.mapstruct;
  requires org.slf4j;
  requires quarkus.core;
  requires quarkus.jackson;

  requires ouranos.core;
  requires ouranos.domain;
  requires ouranos.sp3;
}
