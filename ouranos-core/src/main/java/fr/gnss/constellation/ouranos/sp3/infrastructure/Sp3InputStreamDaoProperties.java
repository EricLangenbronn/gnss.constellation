package fr.gnss.constellation.ouranos.sp3.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "default.products.access")
public class Sp3InputStreamDaoProperties {

  public String ftpServerName;
  public String epochDirectory;

}
