package fr.gnss.constellation.ouranos.api.microprofile.health;

import fr.gnss.constellation.ouranos.orbitdata.sp3.persitence.Sp3StorageDirectory;
import javax.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
@RequiredArgsConstructor
public class AccessibleSp3DirectoryHealthCheck implements HealthCheck {

  private final Sp3StorageDirectory defaultDownloadSp3Directory;

  @Override
  public HealthCheckResponse call() {

    HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("SP3 directory health check");

    try {
      if (defaultDownloadSp3Directory.isAccessible()) {
        if (defaultDownloadSp3Directory.isWritable()) {
          responseBuilder.up();
        } else {
          responseBuilder.down()
              .withData("Impossible d'écrire dans le répertoire SP3 : ", defaultDownloadSp3Directory.getAbsolutePath());
        }
      } else {
        responseBuilder.down()
            .withData("Impossible de trouver le répertoire SP3 : ", defaultDownloadSp3Directory.getAbsolutePath());
      }

    } catch (Exception e) {
      responseBuilder.down()
          .withData("Impossible d'accéder au répertoire SP3 : ", e.getMessage());
    }

    return responseBuilder.build();
  }

}
