package fr.gnss.constellation.ouranos.api.service;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Manifest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ServiceLocator {

	/**
	 * le logger...
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLocator.class);

	/**
	 * Contexte d'application Spring.
	 */
	private static ServiceFactory serviceFactory = null;

	/** Le fichier de configuration Spring globale */
	public static String GLOBAL_AC = "/module/globalAC.xml";

	/** Map des fichiers spring d'initialisation */
	private static Map<String, String> mapFichiersSpring = null;

	private ServiceLocator() {

	}

	/**
	 * Permet de retourner la factory proprement
	 * 
	 * @param p_Cle
	 *            la clé du globalAC
	 * @return la factory
	 */
	public static ServiceFactory getServiceFactory(String p_Cle) {
		if (null == serviceFactory) {
			serviceFactory = initialiserServiceFactory(p_Cle);
		}

		return serviceFactory;
	}

	/**
	 * Permet de retourner la factory proprement
	 * 
	 * @param p_Cle
	 *            la clé du globalAC
	 * @return la factory
	 */
	public static ServiceFactory getServiceFactory(ApplicationContext p_Context) {
		if (null == serviceFactory) {
			serviceFactory = initialiserServiceFactory(p_Context);
		}

		return serviceFactory;
	}

	/**
	 * @param p_Cle
	 *            la clé de la factory
	 * @return la factory
	 */
	public synchronized static ServiceFactory initialiserServiceFactory(String p_Cle) {
		if (null == serviceFactory) {
			try {
				mapFichiersSpring = new HashMap<String, String>();

				String fichierConf = mapFichiersSpring.get(p_Cle);
				if (null == fichierConf) {
					fichierConf = GLOBAL_AC;
				}

				ApplicationContext springContext;
				springContext = new ClassPathXmlApplicationContext(fichierConf);
				initialiserServiceFactory(springContext);
			} catch (Exception e) {
				throw new RuntimeException("Erreur lors de l'initialisation de la factory", e);
			}
		}
		return serviceFactory;
	}

	/**
	 * @param p_Cle
	 *            la clé de la factory
	 * @return la factory
	 */
	public synchronized static ServiceFactory initialiserServiceFactory(ApplicationContext p_Context) {
		if (null == serviceFactory) {
			try {
				if (LOGGER.isInfoEnabled()) {
					String[] beans = p_Context.getBeanDefinitionNames();

					if (beans != null && beans.length > 0) {
						String services = StringUtils.join(beans, ", ");
						LOGGER.info("Services spring : " + services);
					}
				}
				serviceFactory = (ServiceFactory) p_Context.getBean("ouranosServiceFactory");

				// OK pour Tomcat
				LOGGER.info("Version Services : " + serviceFactory.getInformationsVersion());
			} catch (Exception e) {
				String l_msg = "Erreur lors de l'initialisation de la factory";
				LOGGER.error(l_msg, e);
				throw new RuntimeException(l_msg, e);
			}
		}
		return serviceFactory;
	}

	/**
	 * @return la factory
	 */
	public static ServiceFactory getServiceFactory() {
		// par défaut, on initialise tout
		return getServiceFactory(GLOBAL_AC);
	}
}
