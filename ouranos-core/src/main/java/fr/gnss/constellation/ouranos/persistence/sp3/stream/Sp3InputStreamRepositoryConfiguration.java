package fr.gnss.constellation.ouranos.persistence.sp3.stream;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@AllArgsConstructor
public class Sp3InputStreamRepositoryConfiguration {

    // -------------------- Valeurs par défaut des propriétés --------------------

    private static final String FTP_SERVER_NAME = "igs.ensg.ign.fr";
    private static final String EPOCH_DIRECTORY = "/pub/igs/products";

    private final Sp3InputStreamRepositoryProperties sp3InputStreamRepositoryProperties;

    // -------------------- Methodes internes --------------------

    @Produces
    public String getDefaultFtpServerName() {

        if (StringUtils.isNotBlank(sp3InputStreamRepositoryProperties.ftpServerName)) {
            return sp3InputStreamRepositoryProperties.ftpServerName;
        } else {
            return FTP_SERVER_NAME;
        }
    }

    @Produces
    public String getDefaultEpochDirectory() {

        if (StringUtils.isNotBlank(sp3InputStreamRepositoryProperties.epochDirectory)) {
            return sp3InputStreamRepositoryProperties.epochDirectory;
        } else {
            return EPOCH_DIRECTORY;
        }
    }

}
