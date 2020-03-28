package fr.gnss.constellation.ouranos.service.version.strategy;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.version.factory.VersionProcessFactory;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;

public class VersionProcessStrategyImpl {
	
	// -------------------- Propriétés de la classe --------------------
	
    private final VersionProcessFactory versionProcessFactory = new VersionProcessFactory();
    
	// -------------------- Methodes de l'interface --------------------

    public Version processVerion(VersionType versionType, String version) throws BusinessException {

    	IVersionProcessStrategy versionProcessStrategy = versionProcessFactory.getVersionProcessStrategy(versionType);

        return versionProcessStrategy.processVersion(version);

    }

}
