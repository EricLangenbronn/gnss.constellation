package fr.gnss.constellation.ouranos.api.service;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import fr.gnss.constellation.ouranos.service.IConfigurationService;

/**
 * Classe permettant de lancer certaines actions au démarrage de l'application
 * (après chargement du contexte Spring pour être exact).
 * 
 */
@Component("ouranosStartupListener")
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger LOGGER = LoggerFactory.getLogger(StartupListener.class);

	// -------------------- Services --------------------

	@Autowired
	private IConfigurationService configurationService;

	@Autowired
	private Environment env;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		// try {
		ServletContext servletContext = null;
		if (event.getApplicationContext() instanceof WebApplicationContext) {
			servletContext = ((WebApplicationContext) event.getApplicationContext()).getServletContext();
		}

		if (env instanceof ConfigurableEnvironment) {
			MutablePropertySources propertySources = ((ConfigurableEnvironment) env).getPropertySources();
			for (PropertySource ps : propertySources) {
				LOGGER.info(ps.getName()); // if only file based needed then check if instanceof ResourcePropertySource
			}
		}
	}
}