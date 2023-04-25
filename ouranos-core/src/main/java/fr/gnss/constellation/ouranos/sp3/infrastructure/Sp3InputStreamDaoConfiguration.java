package fr.gnss.constellation.ouranos.sp3.infrastructure;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
@AllArgsConstructor
public class Sp3InputStreamDaoConfiguration {

  // -------------------- Valeurs par défaut des propriétés --------------------

  private static final String FTP_SERVER_NAME = "igs.ensg.ign.fr";
  private static final String EPOCH_DIRECTORY = "/pub/igs/products";

  // -------------------- Methodes internes --------------------

  @Produces
  public FtpServerName getDefaultFtpServerName(Sp3InputStreamDaoProperties sp3InputStreamDaoProperties) {
    return new FtpServerName(
        StringUtils.isNotBlank(sp3InputStreamDaoProperties.ftpServerName) ? sp3InputStreamDaoProperties.ftpServerName : FTP_SERVER_NAME
    );
  }

  @Produces
  public EpochDirectory getDefaultEpochDirectory(Sp3InputStreamDaoProperties sp3InputStreamDaoProperties) {
    return new EpochDirectory(
        StringUtils.isNotBlank(sp3InputStreamDaoProperties.epochDirectory) ? sp3InputStreamDaoProperties.epochDirectory : EPOCH_DIRECTORY
    );
  }

}
