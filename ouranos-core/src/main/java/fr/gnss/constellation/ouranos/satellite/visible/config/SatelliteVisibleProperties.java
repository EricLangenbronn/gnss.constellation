package fr.gnss.constellation.ouranos.satellite.visible.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.elevation.mask")
public class SatelliteVisibleProperties {

  public Double elevationMaskDegree;
}