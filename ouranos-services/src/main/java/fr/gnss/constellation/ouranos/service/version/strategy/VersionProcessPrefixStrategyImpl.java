package fr.gnss.constellation.ouranos.service.version.strategy;

import fr.gnss.constellation.ouranos.service.version.exception.VersionException;
import fr.gnss.constellation.ouranos.version.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class VersionProcessPrefixStrategyImpl extends AbstractProcessStrategy implements IVersionProcessStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionProcessPrefixStrategyImpl.class);

    // -------------------- Propriétés de la classe --------------------

    private String regex = "v[0-9][0-9]";
    private Pattern pattern;

    private static final int VERSION_MAX = 1;

    // -------------------- Constructeurs --------------------

    public VersionProcessPrefixStrategyImpl() {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    // -------------------- Methodes de l'interface --------------------

    @Override
    public Version processVersion(String version) {
        int numeroVersion = -1;
        if (this.checkIfValideVersionRegex(this.pattern, version)) {
            numeroVersion = Integer.parseInt(version.substring(1, version.length()));
            if (numeroVersion > VERSION_MAX) {
                String message = "Le numéro de version est supperieur à la version de l'api.";
                throw new VersionException(message);
            }
        } else {
            String message = "La version ne respecte pas la regex.";
            throw new VersionException(message);
        }
        return new Version(numeroVersion);
    }

}
