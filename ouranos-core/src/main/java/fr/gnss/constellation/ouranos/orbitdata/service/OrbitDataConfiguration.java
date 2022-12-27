package fr.gnss.constellation.ouranos.orbitdata.service;


import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@AllArgsConstructor
public class OrbitDataConfiguration {

    private final OrbitsDataProperties orbitsDataProperties;

    @Produces
    public AuthorizedNewDownload getAuthorizedNewDownload() {
        return new AuthorizedNewDownload(orbitsDataProperties.authorizedDownload);
    }
}
