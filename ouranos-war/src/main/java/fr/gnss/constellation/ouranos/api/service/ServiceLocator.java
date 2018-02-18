package fr.gnss.constellation.ouranos.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component("ouranosServiceLocator")
public final class ServiceLocator implements ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLocator.class);

	// -------------------- Constantes --------------------

	private static final String ATTRIBUTE_BUILD_TAG = "Build-Tag";

	// -------------------- Attributs --------------------

	private static ApplicationContext CONTEXTE;

	// -------------------- Getters & Setters --------------------

	public static ApplicationContext getContexte() {
		return CONTEXTE;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXTE = applicationContext;
	}

	// -------------------- Méthodes --------------------

	/**
	 * Cette méthode permet de récupérer les informations de version dans le jar de
	 * l'application.
	 * 
	 * @param p_WebappManifest
	 *            path vers la racine de la webapp
	 * @return les informations de version de Delia sous forme de String
	 */
	public static String getInformationsVersionWebapp(InputStream p_WebappManifest) {
		String informations = "Aucune information de version.";
		try {
			Manifest manifest = new Manifest(p_WebappManifest);
			Attributes mainAttribs = manifest.getMainAttributes();
			String version = mainAttribs.getValue(ATTRIBUTE_BUILD_TAG);
			if (version != null) {
				return version;
			}
		} catch (Throwable t) {
			t.printStackTrace();
			informations = "Erreur lors de la lecture des informations de version de l'application (" + t.getMessage() + ")";
		}
		return informations;
	}

	/**
	 * Liste les versions des différentes librairies du projet.
	 * 
	 * @return une map nom-de-la-librairie => version
	 * @throws TechnicalException
	 */
	public static Map<String, String> getInformationsVersion() throws IOException {
		Map<String, String> result = new HashMap<String, String>();

		ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(CONTEXTE);
		Resource[] resources = resourceResolver.getResources("classpath*:META-INF/MANIFEST.MF");
		for (Resource resource : resources) {
			Manifest manifest = new Manifest(resource.getInputStream());
			Attributes mainAttribs = manifest.getMainAttributes();
			String version = mainAttribs.getValue(ATTRIBUTE_BUILD_TAG);
			if (version != null) {
				result.put(getNomJarFromUri(resource.getURI().toString()), version);
			}
		}

		return result;
	}

	private static String getNomJarFromUri(String uri) {
		String uriJar = uri.substring(0, uri.indexOf('!'));
		String jar = uriJar.substring(uriJar.lastIndexOf('/') + 1);
		return jar;
	}

}
