package fr.gnss.constellation.ouranos.api.service;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.service.flux.IFluxService;
import fr.gnss.constellation.ouranos.service.orbitdata.IOrbitsDataService;
import fr.gnss.constellation.ouranos.service.satelitevisible.ISateliteVisibleService;

public class ServiceFactory {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFactory.class);

	private IOrbitsDataService orbitsDataService = null;

	private ISateliteVisibleService sateliteVisibleService = null;

	private IFluxService fluxService = null;

	/**
	 * Cette méthode permet de récupérer les informations de version dans le jar
	 * de l'application.
	 * 
	 * @return les informations de version de Delia sous forme de String
	 */
	public static String getInformationsVersion() {
		String informations = "Aucune information de version.";
		try {
			String classContainer = ServiceFactory.class.getProtectionDomain().getCodeSource().getLocation().toString();

			// manifest dans le cas d'un JOnAS
			// jonas : URL manifestUrl = new URL("jar:" + classContainer +
			// "!/META-INF/MANIFEST.MF");
			// Tomcat : URL manifestUrl = new URL("jar:" + classContainer +
			// "!/META-INF/MANIFEST.MF");
			// JBoss : URL manifestUrl = new URL("jar:" + classContainer +
			// "/META-INF/MANIFEST.MF");
			URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(manifestUrl.openStream());

			Iterator<Object> it = manifest.getMainAttributes().keySet().iterator();
			boolean trouve = false;

			while (it.hasNext() && false == trouve) {
				Object tmp = it.next();
				String key = tmp.toString();
				if (key.equals("Implementation-Version")) {
					informations = manifest.getMainAttributes().get(tmp).toString();
					trouve = true;
				}
			}
		} catch (Throwable t) {
			informations = "Erreur lors de la lecture des informations de version de l'application Ouranos " + " ("
					+ t.getMessage() + ")";
		}
		return informations;
	}

	/**
	 * Cette méthode permet de récupérer les informations de version dans le jar
	 * de l'application.
	 * 
	 * @param p_WebappManifest
	 *            path vers la racine de la webapp
	 * @return les informations de version de Delia sous forme de String
	 */
	public static String getInformationsVersionWebapp(InputStream p_WebappManifest) {
		String informations = "Aucune information de version.";
		try {
			Manifest manifest = new Manifest(p_WebappManifest);

			Iterator<Object> it = manifest.getMainAttributes().keySet().iterator();
			boolean trouve = false;

			while (it.hasNext() && false == trouve) {
				Object tmp = it.next();
				String key = tmp.toString();
				if (key.equals("Implementation-Version")) {
					informations = manifest.getMainAttributes().get(tmp).toString();
					trouve = true;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			informations = "Erreur lors de la lecture des informations de version de l'application Ouranos ("
					+ t.getMessage() + ")";
		}
		return informations;
	}

	public ISateliteVisibleService getSateliteVisibleService() {
		return this.sateliteVisibleService;
	}

	public IOrbitsDataService getOrbitsDataService() {
		return this.orbitsDataService;
	}

	public IFluxService getFluxService() {
		return this.fluxService;
	}

	public void setOrbitsDataService(IOrbitsDataService orbitsDataService) {
		this.orbitsDataService = orbitsDataService;
	}

	public void setSateliteVisibleService(ISateliteVisibleService sateliteVisibleService) {
		this.sateliteVisibleService = sateliteVisibleService;
	}

	public void setFluxService(IFluxService fluxService) {
		this.fluxService = fluxService;
	}

}