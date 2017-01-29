package fr.gnss.constellation.ouranos.api.service;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ServletContextAware;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.IConfigurationService;
import fr.gnss.constellation.ouranos.service.IPropertiesService;

/**
 * Classe permettant de lancer certaines actions au démarrage de l'application
 * (après chargement du contexte Spring pour être exact).
 * 
 */
public class StartupListener implements ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

	private final static Logger LOGGER = LoggerFactory.getLogger(StartupListener.class);

	// -------------------- Services --------------------

	private IPropertiesService parametreService;
	private IConfigurationService configurationService;

	// -------------------- Attributs --------------------

	private ServletContext context;

	// -------------------- Setters Services --------------------

	public void setPropertiesService(IPropertiesService parametreService) {
		this.parametreService = parametreService;
	}

	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		try {
			// Initialisation du parametreService
			initialiserParametres();
			// Initialisation du configurationService
			configurationService.init();
		} catch (TechnicalException e) {
			LOGGER.error("Erreur lors de l'initialisation de l'application", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	// -------------------- Méthodes internes --------------------

	private void initialiserParametres() throws TechnicalException {
		// NB : ce code reprend le principe de la InitialiserParametrageServlet
		// (api-parametre)
		try {
			LOGGER.info("Chargement de la configuration");

			String nomParametrerepertoireconfiguration = context.getInitParameter("app.conf.dir");
			String nomfichierconfiguration = context.getInitParameter("app.conf.file");

			String repertoireconfiguration = System.getProperty(nomParametrerepertoireconfiguration);

			LOGGER.info("Le répertoire de configuration est  : " + repertoireconfiguration);
			LOGGER.info("Le fichier de configuration est  : " + nomfichierconfiguration);

			parametreService.initConfiguration(repertoireconfiguration, nomfichierconfiguration);

			LOGGER.info("Chargement configuration : OK");
		} catch (Exception e) {
			LOGGER.error("Une erreur est intervenue au chargement de la configuration", e);
			throw new TechnicalException("Une erreur est intervenue au chargement de la configuration", e);
		}
	}

}