package fr.gnss.constellation.ouranos.service.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;

@Service("xmlTemplateService")
public class XmlTemplateService extends AbstractTemplateService {

	public XmlTemplateService(@Qualifier("xmlTemplateEngine") TemplateEngine jsonTemplateEngine,
			@Autowired IConfigurationLogMessageService configurationLogMessageService) {
		super(jsonTemplateEngine, configurationLogMessageService);
	}

}
