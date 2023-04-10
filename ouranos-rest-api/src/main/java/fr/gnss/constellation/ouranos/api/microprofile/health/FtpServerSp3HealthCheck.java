package fr.gnss.constellation.ouranos.api.microprofile.health;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
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
  private final AuthorizedNewSp3Download authorizedNewSp3Download;

  @Override
  public HealthCheckResponse call() {

    HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Ftp server SP3 file health check");

    try {

      if (authorizedNewSp3Download.isAuthorized()) {
        ClientFtp clientFtp = new ClientFtp(ftpServerName);
        clientFtp.openConnection();
        clientFtp.logoutAndCloseConnection();

        responseBuilder.up();
      } else {
        responseBuilder.down()
            .withData("Impossible de se connecter au serveur FTP SP3 non authorisé : ", authorizedNewSp3Download.isAuthorized());
      }

    } catch (Exception e) {
      responseBuilder.down()
          .withData("Impossible de se connecter au serveur FTP SP3 : ", e.getMessage());
    }

    return responseBuilder.build();
  }
}
