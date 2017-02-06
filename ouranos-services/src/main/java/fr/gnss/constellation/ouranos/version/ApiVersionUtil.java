package fr.gnss.constellation.ouranos.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public class ApiVersionUtil {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiVersionUtil.class);

	private String regex = "v[0-9][0-9]";
	private Pattern pattern;
	private Matcher matcher;

	private static final int VERSION_MAX = 1;

	private static ApiVersionUtil INSTANCE = null;

	private ApiVersionUtil() {
		this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public static ApiVersionUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ApiVersionUtil();
		}

		return INSTANCE;
	}

	private boolean checkIfValideVersionRegex(String version) {
		boolean isValideVersion = false;

		this.matcher = this.pattern.matcher(version);
		if (this.matcher.matches()) {
			isValideVersion = true;
		}

		return isValideVersion;
	}

	public Version getVersion(String version) throws BusinessException {
		int numeroVersion = -1;
		if (this.checkIfValideVersionRegex(version)) {
			numeroVersion = Integer.parseInt(version.substring(1, version.length()));
			if (numeroVersion > VERSION_MAX) {
				String message = "Le numéro de version est supperieur à la version de l'api.";
				LOGGER.debug(message);
				throw new BusinessException(message);
			}
		} else {
			String message = "La version ne respecte pas la regex.";
			LOGGER.debug(message);
			throw new BusinessException(message);
		}
		return new Version(numeroVersion);
	}

	public Version getPreviousVersion(Version currentVersion) {
		int version = currentVersion.getVersion();
		Version previousVersion;
		if (version - 1 <= 0) {
			previousVersion = new Version(0);
		} else {
			previousVersion = new Version(version - 1);
		}
		return previousVersion;
	}
}
