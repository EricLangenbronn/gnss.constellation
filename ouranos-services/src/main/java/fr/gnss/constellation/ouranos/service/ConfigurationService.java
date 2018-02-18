package fr.gnss.constellation.ouranos.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

@Service("ouranosConfigurationService")
public class ConfigurationService implements IConfigurationService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

	// -------------------- Nom des propriétés --------------------

	private static final String DEF_DOSSIER_CONFIG_OURANOS = "config";
	private static final String DEF_FICHIER_CONFIG_OURANOS = "ouranos-rest-api.properties";
	private static final String VAR_SYSTEME_OURANOS_DOSSIER_CONF = "OURANOS_REST_API_DIR_CONF";
	private static final String VAR_SYSTEME_OURANOS_FICHIER_CONF = "OURANOS_REST_API_FILE_CONF";

	private static final String PROP_DIRECTORY_SP3_FILE = "repertoire.sp3";
	private static final String PROP_ACCESS_FTP_SP3 = "authorized.access.ftp.sp3";

	// -------------------- Services --------------------

	@Autowired
	@Qualifier("ouranosPropertiesService")
	private IPropertiesService parametreService;

	// -------------------- Valeurs par défaut des propriétés --------------------

	private String directorySp3 = "C:\\";
	private boolean accessFtpSp3 = true;

	// -------------------- Initialisation --------------------

	// NB : l'initialisation n'est pas faite en @PostConstruct via Spring mais
	// après le chargement Spring
	// car on a besoin d'initialiser le parametreService avant (on pourrait le
	// faire ici mais cela n'aide pas
	// la lisibilité).
	@Override
	@PostConstruct
	public void init() throws TechnicalException, BusinessException {
		LOGGER.info("Initialisation de la configuration de l'API.");

		parametreService.initConfiguration(getDossierConfigurationParametrage(), getFichierConfigurationParametrage());

		if (parametreService.exists(PROP_DIRECTORY_SP3_FILE)) {
			directorySp3 = parametreService.getString(PROP_DIRECTORY_SP3_FILE);
		}

		if (parametreService.exists(PROP_ACCESS_FTP_SP3)) {
			accessFtpSp3 = parametreService.getBoolean(PROP_ACCESS_FTP_SP3);
		}
	}

	// -------------------- Setters Services --------------------

	public void setParametreService(IPropertiesService parametreService) {
		this.parametreService = parametreService;
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String getDirectorySp3() {
		return this.directorySp3;
	}

	@Override
	public boolean isaccessFtpSp3() {
		return this.accessFtpSp3;
	}

	// -------------------- Methodes internes --------------------

	/**
	 * @return le dossier contenant la configuration
	 */
	protected String getDossierConfigurationParametrage() {
		// Recuperation propriété systême
		String dossierConfig = System.getProperty(VAR_SYSTEME_OURANOS_DOSSIER_CONF);

		if (dossierConfig != null && !dossierConfig.isEmpty()) {
			LOGGER.info("Le dossier de paramétrage fourni par la variable systeme -D" + VAR_SYSTEME_OURANOS_DOSSIER_CONF + " a été trouvé : " + dossierConfig);
		} else {
			LOGGER.warn("Le dossier de paramétrage fourni par la variable systeme -D" + VAR_SYSTEME_OURANOS_DOSSIER_CONF
					+ " n'est pas renseigné, utilisation de la valeur par défaut : " + DEF_DOSSIER_CONFIG_OURANOS);
			dossierConfig = DEF_DOSSIER_CONFIG_OURANOS;
		}

		return dossierConfig;
	}

	/**
	 * @return le fichier contenant la configuration
	 */
	protected String getFichierConfigurationParametrage() {
		// Recuperation propriété systême
		String fichierConfig = System.getProperty(VAR_SYSTEME_OURANOS_FICHIER_CONF);

		if (fichierConfig != null && !fichierConfig.isEmpty()) {
			LOGGER.info("Le fichier de paramétrage fourni par la variable systeme -D" + VAR_SYSTEME_OURANOS_FICHIER_CONF + " a été trouvé : " + fichierConfig);
		} else {
			LOGGER.warn("Le fichier de paramétrage fourni par la variable systeme -D" + VAR_SYSTEME_OURANOS_FICHIER_CONF
					+ " n'est pas renseigné, utilisation de la valeur par défaut : " + DEF_FICHIER_CONFIG_OURANOS);
			fichierConfig = DEF_FICHIER_CONFIG_OURANOS;
		}

		return fichierConfig;
	}

}
