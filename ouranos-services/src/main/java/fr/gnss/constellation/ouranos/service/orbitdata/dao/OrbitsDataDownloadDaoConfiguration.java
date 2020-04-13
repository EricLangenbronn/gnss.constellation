package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.gnss.constellation.ouranos.toolbox.ClientFtpSp3FileConfiguration;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OrbitsDataDownloadDaoProperties.class)
public class OrbitsDataDownloadDaoConfiguration {

	// -------------------- Valeurs par défaut des propriétés --------------------

	private static final String FTP_SERVER_NAME = "igs.ensg.ign.fr";
	private static final String EPOCH_DIRECTORY = "/pub/igs/products";

	// -------------------- Propriétés de la classe --------------------

	private final OrbitsDataDownloadDaoProperties props;

	// -------------------- Methodes internes --------------------

	@Bean
	public ClientFtpSp3FileConfiguration getClientFtpSp3FileConfiguration() {

		ClientFtpSp3FileConfiguration clientFtpSp3FileConfiguration = new ClientFtpSp3FileConfiguration();

		if (StringUtils.isNotBlank(props.getFtpServerName())) {
			clientFtpSp3FileConfiguration.setFtpServerName(props.getFtpServerName());
		} else {
			clientFtpSp3FileConfiguration.setFtpServerName(FTP_SERVER_NAME);
		}

		if (StringUtils.isNotBlank(props.getFtpServerName())) {
			clientFtpSp3FileConfiguration.setEpochDirectory(props.getEpochDirectory());
		} else {
			clientFtpSp3FileConfiguration.setEpochDirectory(EPOCH_DIRECTORY);
		}

		return clientFtpSp3FileConfiguration;

	}

}
