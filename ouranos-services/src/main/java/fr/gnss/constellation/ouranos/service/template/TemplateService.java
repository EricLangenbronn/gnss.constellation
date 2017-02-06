package fr.gnss.constellation.ouranos.service.template;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateService implements ITemplateService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);

	private VelocityEngine velocityEngine;

	public void test() {
		// velocityEngine.getTemplate(name)

	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}
