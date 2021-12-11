package fr.gnss.constellation.ouranos.service.template;

import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.Version;

import java.util.Map;

public interface ITemplateService {

    StringBuffer generateFlux(String templatePath, Map<String, Object> informations);

    String getTemplate(String resource, ResourceType resourceType, Version version);
}
