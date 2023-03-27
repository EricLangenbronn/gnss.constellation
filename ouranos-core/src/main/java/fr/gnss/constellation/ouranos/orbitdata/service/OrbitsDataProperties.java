package fr.gnss.constellation.ouranos.orbitdata.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orbit.data")
public class OrbitsDataProperties {

  public boolean authorizedDownload;
}
