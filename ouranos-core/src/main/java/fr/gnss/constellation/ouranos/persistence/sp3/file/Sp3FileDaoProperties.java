package fr.gnss.constellation.ouranos.persistence.sp3.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.sp3")
public class Sp3FileDaoProperties {

    public String directory;

}
