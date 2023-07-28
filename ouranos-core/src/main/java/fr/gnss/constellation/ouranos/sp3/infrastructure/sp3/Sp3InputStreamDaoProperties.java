package fr.gnss.constellation.ouranos.sp3.infrastructure.sp3;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "default.products.access")
public interface Sp3InputStreamDaoProperties {

  String ftpServerName();

  String epochDirectory();

}
