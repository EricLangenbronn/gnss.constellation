package fr.gnss.constellation.ouranos.service.version.strategy;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.version.Version;

public interface IVersionProcessStrategy {

	Version processVersion(String version) throws BusinessException;
}
