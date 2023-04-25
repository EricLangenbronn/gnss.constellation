package fr.gnss.constellation.ouranos.orbitdata.service;

import fr.gnss.constellation.ouranos.orbitdata.service.parallel.OrbitDataParallelService;
import fr.gnss.constellation.ouranos.orbitdata.service.parallel.OrbitDataReadAndParseFactory;
import fr.gnss.constellation.ouranos.orbitdata.service.sequential.OrbitDataSequentialService;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.AllArgsConstructor;


@ApplicationScoped
@AllArgsConstructor
public class OrbitDataConfiguration {

  @Produces
  @IfBuildProperty(name = "ouranos.parallel-processing.enabled", stringValue = "true")
  public IOrbitDataService getOrbitDataParallelService(ISp3Service sp3Service, OrbitDataReadAndParseFactory orbitDataReadAndParseFactory) {
    
    return new OrbitDataParallelService(sp3Service, orbitDataReadAndParseFactory);
  }

  @Produces
  @DefaultBean
  public IOrbitDataService getOrbitDataSequentialService(ISp3Service sp3Service) {

    return new OrbitDataSequentialService(sp3Service);
  }
}
