package fr.gnss.constellation.ouranos.service.version;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.service.version.strategy.VersionProcessStrategyImpl;
import fr.gnss.constellation.ouranos.version.Version;

@Service("versionService")
public class VersionServiceImpl implements IVersionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionServiceImpl.class);

	// -------------------- Propriétés de la classe --------------------

	private VersionProcessStrategyImpl versionProcessStrategy;

	// -------------------- Constructeurs --------------------
	
	public VersionServiceImpl() {
		this.versionProcessStrategy = new VersionProcessStrategyImpl();
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public Version getVersion(VersionType versionType, String version) throws BusinessException {
		return this.versionProcessStrategy.processVerion(versionType, version);
	}

	@Override
	public Version checkIfVersionOrPreviousIsContains(Version requestedVersion, List<Version> availabelVersion) throws BusinessException {

		availabelVersion.add(requestedVersion);
		Collections.sort(availabelVersion);

		int index = availabelVersion.lastIndexOf(requestedVersion);

		Version version;
		if (index >= 1) {
			// TODO: point d'intérêt :)
			/* :D drôle comme manière de faire, non ? :D */
			version = availabelVersion.get(index - 1);
		} else {
			String message = "Aucun numéro de version valide n'est présent dans la liste pour cette version [requestedVersion=" + requestedVersion + "]";
			LOGGER.error(message);
			throw new BusinessException(message);
		}

		return version;

	}

}
