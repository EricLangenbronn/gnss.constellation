package fr.gnss.constellation.ouranos.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

@Configuration
@ComponentScan
@PropertySources({ @PropertySource("classpath:ouranos-rest-api.properties"),
		@PropertySource(value = "file:config/${OURANOS_REST_API_FILE_CONF}", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${OURANOS_REST_API_DIR_CONF}/${OURANOS_REST_API_FILE_CONF}", ignoreResourceNotFound = true), })
@Service("ouranosConfigurationService")
public class ConfigurationService implements IConfigurationService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

	// -------------------- Nom des propriétés --------------------

	private static final String PROP_DIRECTORY_SP3_FILE = "repertoire.sp3";
	private static final String PROP_ACCESS_FTP_SP3 = "authorized.access.ftp.sp3";

	// -------------------- Services --------------------

	@Autowired
	private Environment environment;

	// -------------------- Valeurs par défaut des propriétés --------------------

	private String directorySp3 = "C:\\";
	private Boolean accessFtpSp3 = Boolean.TRUE;

	// -------------------- Initialisation --------------------

	@Override
	@PostConstruct
	public void init() throws TechnicalException, BusinessException {
		LOGGER.info("Initialisation de la configuration de l'API.");

		if (environment.containsProperty(PROP_DIRECTORY_SP3_FILE)) {
			directorySp3 = environment.getProperty(PROP_DIRECTORY_SP3_FILE, String.class);
		}

		if (environment.containsProperty(PROP_ACCESS_FTP_SP3)) {
			accessFtpSp3 = environment.getProperty(PROP_ACCESS_FTP_SP3, Boolean.class);
		}
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String getDirectorySp3() {
		return this.directorySp3;
	}

	@Override
	public Boolean isaccessFtpSp3() {
		return this.accessFtpSp3;
	}

}
