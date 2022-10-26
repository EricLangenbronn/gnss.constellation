package fr.gnss.constellation.ouranos.persistence.sp3.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sp3")
public class Sp3FileRepositoryProperties {

    public String directory;

}
