package fr.gnss.constellation.ouranos;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ouranos")
public class OuranosProperties {

  public boolean parallelProcessing;
}
