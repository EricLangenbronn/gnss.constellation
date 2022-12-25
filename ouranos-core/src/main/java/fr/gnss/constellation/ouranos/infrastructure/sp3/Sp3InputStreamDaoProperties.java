package fr.gnss.constellation.ouranos.infrastructure.sp3;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "default.products.access")
public class Sp3InputStreamDaoProperties {

    public String ftpServerName;
    public String epochDirectory;

}
