package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.Map;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.Version;

public interface IResponseResourceService {

	String getFluxSateliteVisible(String resource, ResourceType resourceType, Version version,
			Map<String, Object> informations) throws TechnicalException, BusinessException;
}
