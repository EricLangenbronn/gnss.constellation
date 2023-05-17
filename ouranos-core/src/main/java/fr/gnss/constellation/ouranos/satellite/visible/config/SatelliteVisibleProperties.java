package fr.gnss.constellation.ouranos.satellite.visible.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "default.elevation.mask")
public interface SatelliteVisibleProperties {

  Double elevationMaskDegree();
}
