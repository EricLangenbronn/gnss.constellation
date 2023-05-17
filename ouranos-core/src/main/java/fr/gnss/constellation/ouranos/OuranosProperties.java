package fr.gnss.constellation.ouranos;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "ouranos")
public interface OuranosProperties {

  boolean parallelProcessing();
}
