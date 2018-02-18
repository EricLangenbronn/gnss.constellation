package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.service.template.ITemplateService;
import fr.gnss.constellation.ouranos.version.Version;

@Service("responseResourceService")
public class ResponseResourceService implements IResponseResourceService {

	@Autowired
	private ITemplateService templateService;

	@Override
	public String getFluxSateliteVisible(String resource, ResourceType resourceType, Version version,
			Map<String, Object> informations) throws TechnicalException, BusinessException {

		String template = this.templateService.getTemplate(resource, resourceType, version);

		StringBuffer fluxGenereted = this.templateService.generateFlux(template, informations);

		return fluxGenereted.toString();
	}

}
