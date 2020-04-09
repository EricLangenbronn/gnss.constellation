package fr.gnss.constellation.ouranos.service.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;

@Service("jsonTemplateService")
public class JsonTemplateService extends AbstractTemplateService {

	public JsonTemplateService(@Qualifier("jsonTemplateEngine") TemplateEngine jsonTemplateEngine,
			@Autowired IConfigurationLogMessageService configurationLogMessageService) {
		super(jsonTemplateEngine, configurationLogMessageService);
	}

}
