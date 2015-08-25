package fr.gnss.constellation.ouranos.util;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.Utils.ParameterUtils;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public class ConfigurationLoader {
	/**
	 * Chemin où trouver les fichiers sp3 par défaut.
	 */
	public static final String REPERTOIRE_SP3 = "repertoire.sp3";

	/**
	 * Nom du fichier contenant les properties.
	 */
	private static final String PROPS_NAME = "services.properties";

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(ConfigurationLoader.class);

	/**
	 * Ensemble de propriétée.
	 */
	private static Properties properties = new Properties();

	static {
		try {
			LOGGER.info("Chargement du paramtrage " + PROPS_NAME);
			properties = ParameterUtils.load(PROPS_NAME);

			String repertoireSp3 = properties.getProperty(REPERTOIRE_SP3);
			File repertoire = new File(repertoireSp3);
			if ((false == repertoire.exists()) || (false == repertoire.isDirectory())) {
				LOGGER.error(
						"Arrêt de l'application. Erreur GRAVE : impossible de charger le paramétrage de l'application : "
								+ PROPS_NAME + ", entrée : " + REPERTOIRE_SP3 + ", value : "
								+ repertoire.getAbsolutePath());
				{
					System.exit(1);
				}
			}
		} catch (TechnicalException e) {
			LOGGER.error(
					"Arrêt de l'application. Erreur GRAVE : impossible de charger le paramétrage de l'application : "
							+ PROPS_NAME,
					e);
			{
				System.exit(1);
			}
		}
	}

	/**
	 * Methode permettant d'obtenir une proprietés via son nom.
	 * 
	 * @param pKey
	 *            Nom de la propriétée
	 * @return La valeur de la propriétée
	 */
	public static String getProperty(String pKey) {
		return properties.getProperty(pKey);
	}

	/**
	 * Méthode permettant d'ajouter une proprietés.
	 * 
	 * @param pKey
	 *            Nom de la propriétée
	 * @param pValue
	 *            Valeur de la propriétée
	 */
	public static void setProperty(String pKey, String pValue) {
		properties.setProperty(pKey, pValue);
	}

	/**
	 * Méthode permettant de récuperer l'ensemble des propriétées.
	 * 
	 * @return L'ensemble des propriétées.
	 */
	public static Set<String> stringPropertyNames() {
		return properties.stringPropertyNames();
	}
}
