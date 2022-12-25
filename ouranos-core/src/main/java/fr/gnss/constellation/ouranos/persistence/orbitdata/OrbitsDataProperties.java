package fr.gnss.constellation.ouranos.persistence.orbitdata;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orbit.data")
public class OrbitsDataProperties {

    public boolean authorizedDownload;
}
