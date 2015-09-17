package fr.gnss.constellation.ouranos.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public class ParameterUtils {

	private ParameterUtils() {
	}

	/**
	 * Load a properties file from the classpath
	 * 
	 * @param propsName
	 * @return Properties
	 * @throws Exception
	 */
	public static Properties load(String propsName) throws TechnicalException {
		Properties props = new Properties();
		try {
			URL url = ClassLoader.getSystemResource(propsName);
			props.load(url.openStream());
		} catch (Exception e) {
			String message = "Impossible de charger le properities à partir du classpath [propsName=" + propsName + "]";
			throw new TechnicalException(message, e);
		}
		return props;
	}

	/**
	 * Load a Properties File
	 * 
	 * @param propsFile
	 * @return Properties
	 * @throws IOException
	 */
	public static Properties load(File propsFile) throws TechnicalException {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propsFile);
			props.load(fis);
			fis.close();
		} catch (IOException ex) {
			String message = "Impossible de charger le fichier de properties [propsFile=" + propsFile + "]";
			throw new TechnicalException(message, ex);
		}
		return props;
	}
}
