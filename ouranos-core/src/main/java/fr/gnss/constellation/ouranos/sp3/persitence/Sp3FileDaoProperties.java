package fr.gnss.constellation.ouranos.sp3.persitence;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "default.sp3")
public interface Sp3FileDaoProperties {

  String directory();

}
