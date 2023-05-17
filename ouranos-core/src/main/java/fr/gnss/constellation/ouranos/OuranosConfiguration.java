package fr.gnss.constellation.ouranos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class OuranosConfiguration {

  @Produces
  public AuthorizedParallelProcessing getAuthorizedParallelProcessing(OuranosProperties ouranosProperties) {
    return new AuthorizedParallelProcessing(ouranosProperties.parallelProcessing());
  }
}
