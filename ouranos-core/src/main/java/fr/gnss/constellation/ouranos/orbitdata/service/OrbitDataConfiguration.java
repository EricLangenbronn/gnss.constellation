package fr.gnss.constellation.ouranos.orbitdata.service;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class OrbitDataConfiguration {

  private final OrbitsDataProperties orbitsDataProperties;

  @Produces
  public AuthorizedNewDownload getAuthorizedNewDownload() {
    return new AuthorizedNewDownload(orbitsDataProperties.authorizedDownload);
  }
}
