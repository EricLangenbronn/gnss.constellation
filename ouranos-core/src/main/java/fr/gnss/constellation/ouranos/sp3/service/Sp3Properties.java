package fr.gnss.constellation.ouranos.sp3.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sp3")
public class Sp3Properties {

  public boolean authorizedDownload;
}
