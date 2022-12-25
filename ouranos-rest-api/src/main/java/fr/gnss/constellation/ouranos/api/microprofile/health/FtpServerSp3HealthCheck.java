package fr.gnss.constellation.ouranos.api.microprofile.health;

import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import fr.gnss.constellation.ouranos.infrastructure.sp3.Sp3InputStreamDaoConfiguration;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
@RequiredArgsConstructor
public class FtpServerSp3HealthCheck implements HealthCheck {

    private final Sp3InputStreamDaoConfiguration sp3InputStreamDaoConfiguration;

    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Ftp server SP3 file health check");

        try {

            ClientFtp clientFtp = new ClientFtp(sp3InputStreamDaoConfiguration.getDefaultFtpServerName());
            clientFtp.openConnection();
            clientFtp.logoutAndCloseConnection();

            responseBuilder.up();
        } catch (Exception e) {
            responseBuilder.down()
                    .withData("error", e.getMessage());
        }

        return responseBuilder.build();
    }
}
