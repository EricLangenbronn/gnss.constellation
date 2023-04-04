package fr.gnss.constellation.ouranos.api.microprofile.health;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import fr.gnss.constellation.ouranos.orbitdata.service.AuthorizedNewDownload;
import javax.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
@RequiredArgsConstructor
public class FtpServerSp3HealthCheck implements HealthCheck {

  private final FtpServerName ftpServerName;
  private final AuthorizedNewDownload authorizedNewDownload;

  @Override
  public HealthCheckResponse call() {

    HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Ftp server SP3 file health check");

    try {

      if (authorizedNewDownload.isAuthorized()) {
        ClientFtp clientFtp = new ClientFtp(ftpServerName);
        clientFtp.openConnection();
        clientFtp.logoutAndCloseConnection();

        responseBuilder.up();
      } else {
        responseBuilder.down()
            .withData("Impossible de se connecter au serveur FTP SP3 non authorisé : ", authorizedNewDownload.isAuthorized());
      }

    } catch (Exception e) {
      responseBuilder.down()
          .withData("Impossible de se connecter au serveur FTP SP3 : ", e.getMessage());
    }

    return responseBuilder.build();
  }
}
