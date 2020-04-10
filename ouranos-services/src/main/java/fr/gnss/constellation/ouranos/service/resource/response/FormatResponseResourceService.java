package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.service.template.ITemplateService;
import fr.gnss.constellation.ouranos.version.Version;

@Service("responseResourceService")
public class FormatResponseResourceService implements IFormatResponseResourceService {

	private final ITemplateService jsonTemplateService;
	private final ITemplateService xmlTemplateService;

	public FormatResponseResourceService(@Qualifier("jsonTemplateService") ITemplateService jsonTemplateService,
			@Qualifier("xmlTemplateService") ITemplateService xmlTemplateService) {
		this.jsonTemplateService = jsonTemplateService;
		this.xmlTemplateService = xmlTemplateService;
	}

	@Override
	public StringBuffer getFluxSateliteVisible(String resource, ResourceType resourceType, Version version, Map<String, Object> informations)
			throws TechnicalException, BusinessException {

		StringBuffer fluxGenereted = new StringBuffer();
		if (ResourceType.xml.equals(resourceType)) {
			String template = this.xmlTemplateService.getTemplate(resource, resourceType, version);
			fluxGenereted = this.xmlTemplateService.generateFlux(template, informations);
		} else {
			String template = this.jsonTemplateService.getTemplate(resource, resourceType, version);
			fluxGenereted = this.jsonTemplateService.generateFlux(template, informations);
		}

		return fluxGenereted;
	}

}
