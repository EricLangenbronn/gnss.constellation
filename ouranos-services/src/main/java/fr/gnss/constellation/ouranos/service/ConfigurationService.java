package fr.gnss.constellation.ouranos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationService implements IConfigurationService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

	// -------------------- Nom des propriétés --------------------

	private static final String PROP_DIRECTORY_SP3_FILE = "repertoire.sp3";
	private static final String PROP_ACCESS_FTP_SP3 = "access.ftp.sp3";

	// -------------------- Services --------------------

	private IPropertiesService parametreService;

	// -------------------- Valeurs par défaut des propriétés
	// --------------------

	private String directorySp3;
	private boolean accessFtpSp3 = true;

	// -------------------- Initialisation --------------------

	// NB : l'initialisation n'est pas faite en @PostConstruct via Spring mais
	// après le chargement Spring
	// car on a besoin d'initialiser le parametreService avant (on pourrait le
	// faire ici mais cela n'aide pas
	// la lisibilité).
	@Override
	public void init() {
		LOGGER.info("Initialisation de la configuration de l'API.");

		if (parametreService.exists(PROP_DIRECTORY_SP3_FILE)) {
			accessFtpSp3 = parametreService.getBoolean(PROP_DIRECTORY_SP3_FILE);
		}
		if (parametreService.exists(PROP_ACCESS_FTP_SP3)) {
			directorySp3 = parametreService.getString(PROP_ACCESS_FTP_SP3);
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

}
