package fr.gnss.constellation.ouranos.service.template;

import java.util.Map;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.Version;

public interface ITemplateService {

	StringBuffer generateFlux(String templatePath, Map<String, Object> informations) throws BusinessException;

	String getTemplate(String resource, ResourceType resourceType, Version version) throws BusinessException;
}
