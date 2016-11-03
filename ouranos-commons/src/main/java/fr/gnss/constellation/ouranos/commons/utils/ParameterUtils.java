package fr.gnss.constellation.ouranos.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public class ParameterUtils {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterUtils.class);

	private ParameterUtils() {
	}

	/**
	 * Charge un fichier de paramétrage sous forme d'objet Properties.
	 * <li>Cherche d'abord dans le dossier de configuration externe en paramètre
	 * <li>Puis dans le classpath "/" + nomFichierConfig
	 * <li>Le cas échéant dans le classpath "/defaut/" + nomFichierConfig
	 * 
	 * @param p_nomDossierConfig
	 *            nom du dossier de paramétrage (ce dossier doit se situer au
	 *            niveau ./ correspondant à l'emplacement de l'exécution de
	 *            l'application) ex. "config" pour ./config
	 * @param p_nomFichierConfig
	 *            nom du fichier de properties à charger (ex.
	 *            "config.properties")
	 * @return properties chargée
	 * @throws TechnicalException
	 *             si erreur technique
	 * @throws BusinessException
	 *             si erreur métier
	 */
	public static Properties loadParameters(final String p_nomDossierConfig, final String p_nomFichierConfig)
			throws TechnicalException, BusinessException {
		Properties l_prop = new Properties();
		FileInputStream l_fichierConfExterneInputStream = null;
		String l_cheminExternalConf = FilenameUtils
				.normalizeNoEndSeparator(p_nomDossierConfig + File.separator + p_nomFichierConfig);
		LOGGER.info("Chemin du fichier de configuration externe recherché : " + l_cheminExternalConf);
		// Ressource dans le classPath

		LOGGER.info("Recherche de fichier de paramétrage externe dans : " + l_cheminExternalConf);
		File l_fichierConfigExterne = new File(l_cheminExternalConf);
		if (l_fichierConfigExterne.exists()) {
			LOGGER.info("Un fichier de paramétrage externe a été trouvé : " + l_cheminExternalConf);

			try {
				l_fichierConfExterneInputStream = new FileInputStream(l_fichierConfigExterne);
				l_prop.load(l_fichierConfExterneInputStream);
			} catch (FileNotFoundException l_e) {
				String l_message = "Fichier non trouvé " + l_fichierConfigExterne;
				LOGGER.error(l_message, l_e);
				throw new BusinessException(l_message, l_e);
			} catch (IOException l_e) {
				String l_message = "Erreur d'entree sortie " + l_fichierConfigExterne;
				LOGGER.error(l_message, l_e);
				throw new TechnicalException(l_message, l_e);
			} finally {
				IOUtils.closeQuietly(l_fichierConfExterneInputStream);
			}

		} else {
			Properties props = loadParametersFromClasspath(p_nomDossierConfig + "/" + p_nomFichierConfig);
			if (null == props) {
				props = loadParametersFromClasspath(p_nomFichierConfig);
			}
			if (null == props) {
				props = loadParametersFromClasspath("defaut/" + p_nomFichierConfig);
			}
			if (null != props) {
				l_prop = props;
			}
		}
		return l_prop;
	}

	public static Properties loadParametersFromClasspath(final String p_CheminFichierConfig) throws TechnicalException {
		Properties l_prop = null;
		LOGGER.info("Tentative de chargement de la configuration à partir du classpath dans :" + p_CheminFichierConfig);
		InputStream l_confRepClassPath = null;
		l_confRepClassPath = ParameterUtils.class.getClassLoader().getResourceAsStream(p_CheminFichierConfig);
		if (l_confRepClassPath != null) {
			LOGGER.info("Chargement de la configuration à partir du classpath : "
					+ ParameterUtils.class.getClassLoader().getResource(p_CheminFichierConfig));
			try {
				l_prop = new Properties();
				l_prop.load(l_confRepClassPath);
			} catch (IOException l_e) {
				String l_message = "Erreur d'entree sortie : " + p_CheminFichierConfig;
				LOGGER.error(l_message, l_e);
				throw new TechnicalException(l_message, l_e);
			}
		}
		return l_prop;
	}
}
