package fr.gnss.constellation.ouranos.repository.sp3.stream;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(Sp3InputStreamRepositoryProperties.class)
@RequiredArgsConstructor
public class Sp3InputStreamRepositoryConfiguration {

    // -------------------- Valeurs par défaut des propriétés --------------------

    private static final String FTP_SERVER_NAME = "igs.ensg.ign.fr";
    private static final String EPOCH_DIRECTORY = "/pub/igs/products";

    // -------------------- Methodes internes --------------------

    @Bean("ftpServerName")
    public String getFtpServerName(Sp3InputStreamRepositoryProperties props) {

        if (StringUtils.isNotBlank(props.getFtpServerName())) {
            return props.getFtpServerName();
        } else {
            return FTP_SERVER_NAME;
        }
    }

    @Bean("epochDirectory")
    public String getEpochDirectory(Sp3InputStreamRepositoryProperties props) {

        if (StringUtils.isNotBlank(props.getEpochDirectory())) {
            return props.getEpochDirectory();
        } else {
            return EPOCH_DIRECTORY;
        }
    }

}
