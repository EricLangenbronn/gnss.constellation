package fr.gnss.constellation.ouranos.orbitdata.sp3.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class Sp3Configuration {

  private final Sp3Properties sp3Properties;

  @Produces
  public AuthorizedParallelSp3Download getDefaultSp3StorageDirectory() {
    return new AuthorizedParallelSp3Download(sp3Properties.downloadParallel);
  }

  @Produces
  public AuthorizedNewSp3Download getAuthorizedNewSp3Download() {
    return new AuthorizedNewSp3Download(sp3Properties.authorizedDownload);
  }
}
