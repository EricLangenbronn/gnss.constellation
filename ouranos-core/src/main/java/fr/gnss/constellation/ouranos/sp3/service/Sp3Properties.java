package fr.gnss.constellation.ouranos.sp3.service;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "sp3")
public interface Sp3Properties {

  boolean authorizedDownload();
}
