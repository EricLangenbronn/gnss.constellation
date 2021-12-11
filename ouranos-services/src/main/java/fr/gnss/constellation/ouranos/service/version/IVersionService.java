package fr.gnss.constellation.ouranos.service.version;

import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;

import java.util.List;

public interface IVersionService {

    Version getVersion(VersionType versionType, String version);

    Version checkIfVersionOrPreviousIsContains(Version requestedVersion, List<Version> availabelVersion);

}
