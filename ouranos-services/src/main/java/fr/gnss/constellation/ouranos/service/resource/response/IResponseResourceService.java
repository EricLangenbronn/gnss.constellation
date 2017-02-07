package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.Map;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.version.Version;

public interface IResponseResourceService {

	String getSateliteVisible(String resource, Version version, Map<String, Object> informations)
			throws TechnicalException, BusinessException;
}
