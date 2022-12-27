package fr.gnss.constellation.ouranos.orbitdata.sp3.persitence;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.sp3")
public class Sp3FileDaoProperties {

    public String directory;

}
