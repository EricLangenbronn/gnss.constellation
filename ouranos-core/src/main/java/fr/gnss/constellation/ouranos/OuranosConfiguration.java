package fr.gnss.constellation.ouranos;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class OuranosConfiguration {

  @Produces
  public AuthorizedParallelProcessing getAuthorizedParallelProcessing(OuranosProperties ouranosProperties) {
    return new AuthorizedParallelProcessing(ouranosProperties.parallelProcessing);
  }
}
