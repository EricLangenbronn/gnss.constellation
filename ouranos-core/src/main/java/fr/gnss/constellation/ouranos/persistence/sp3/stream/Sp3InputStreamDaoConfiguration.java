package fr.gnss.constellation.ouranos.persistence.sp3.stream;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@AllArgsConstructor
public class Sp3InputStreamDaoConfiguration {

    // -------------------- Valeurs par défaut des propriétés --------------------

    private static final String FTP_SERVER_NAME = "igs.ensg.ign.fr";
    private static final String EPOCH_DIRECTORY = "/pub/igs/products";

    private final Sp3InputStreamDaoProperties sp3InputStreamDaoProperties;

    // -------------------- Methodes internes --------------------

    @Produces
    public FtpServerName getDefaultFtpServerName() {
        return new FtpServerName(StringUtils.isNotBlank(sp3InputStreamDaoProperties.ftpServerName) ? sp3InputStreamDaoProperties.ftpServerName : FTP_SERVER_NAME);
    }

    @Produces
    public EpochDirectory getDefaultEpochDirectory() {
        return new EpochDirectory(StringUtils.isNotBlank(sp3InputStreamDaoProperties.epochDirectory) ? sp3InputStreamDaoProperties.epochDirectory : EPOCH_DIRECTORY);
    }

}
