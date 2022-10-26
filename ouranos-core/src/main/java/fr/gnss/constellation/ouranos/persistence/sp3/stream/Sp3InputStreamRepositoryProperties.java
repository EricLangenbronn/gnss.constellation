package fr.gnss.constellation.ouranos.persistence.sp3.stream;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "products.access")
public class Sp3InputStreamRepositoryProperties {

    public String ftpServerName;
    public String epochDirectory;

}
