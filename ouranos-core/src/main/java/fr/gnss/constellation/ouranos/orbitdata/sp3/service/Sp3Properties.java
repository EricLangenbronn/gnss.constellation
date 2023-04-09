package fr.gnss.constellation.ouranos.orbitdata.sp3.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sp3")
public class Sp3Properties {

  public boolean downloadParallel;

  public boolean authorizedDownload;
}
