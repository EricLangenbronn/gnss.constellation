package fr.gnss.constellation.ouranos.service.version;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;

public interface IVersionService {
	
	
	Version getVersion(VersionType versionType, String version) throws BusinessException;
	
	Version checkIfVersionOrPreviousIsContains(Version requestedVersion, List<Version> availabelVersion) throws BusinessException;

}
