package fr.gnss.constellation.ouranos.service.version.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractProcessStrategy {
	
	// -------------------- Methodes internes --------------------

	public boolean checkIfValideVersionRegex(Pattern pattern, String version) {
		boolean isValideVersion = false;

		Matcher matcher = pattern.matcher(version);
		if (matcher.matches()) {
			isValideVersion = true;
		}

		return isValideVersion;
	}

}
