package fr.gnss.constellation.ouranos.service.resource.response;

import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.Version;

import java.util.Map;

public interface IFormatResponseResourceService {

    StringBuffer getFluxSateliteVisible(String resource, ResourceType resourceType, Version version
            , Map<String, Object> informations);
}
