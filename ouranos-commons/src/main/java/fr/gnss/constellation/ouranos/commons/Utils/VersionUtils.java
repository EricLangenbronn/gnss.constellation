package fr.gnss.constellation.ouranos.commons.Utils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.jar.Manifest;

public class VersionUtils {

	/**
	 * Cette méthode permet de récupérer les informations de version dans le jar
	 * de l'application.
	 * 
	 * @param p_WebappManifest
	 *            path vers la racine de la webapp
	 * @return les informations de version de l'application sous forme de String
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
				if (key.equals("Build-Tag")) {
					informations = manifest.getMainAttributes().get(tmp).toString();
					trouve = true;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			informations = "Erreur lors de la lecture des informations de version de l'application GNSS-Constellation" + " ("
					+ t.getMessage() + ")";
		}
		return informations;
	}

}
