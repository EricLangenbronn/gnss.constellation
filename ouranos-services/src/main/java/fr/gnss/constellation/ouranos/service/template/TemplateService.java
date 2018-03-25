package fr.gnss.constellation.ouranos.service.template;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.Constante;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.service.template.velocity.LocalDateTimeTool;
import fr.gnss.constellation.ouranos.service.template.velocity.SatellitePositionTool;
import fr.gnss.constellation.ouranos.version.Version;

@Service("templateService")
public class TemplateService implements ITemplateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);

	// -------------------- Services --------------------

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private IConfigurationLogMessageService configurationLogMessageService;

	// -------------------- Propriétés de la classe --------------------

	private TemplateUtils templateUtils = new TemplateUtils();

	// -------------------- Methodes de l'interface --------------------

	@Override
	public StringBuffer generateFlux(String templatePath, Map<String, Object> informations) throws BusinessException {
		VelocityContext context = new VelocityContext();
		for (Entry<String, Object> info : informations.entrySet()) {
			context.put(info.getKey(), info.getValue());
		}

		// ajout lib pour formatage nombre + date
		context.put("localDateTimeTool", new LocalDateTimeTool());
		context.put("satellitePositionTool", new SatellitePositionTool());
		context.put("dateToolLocale", Locale.ENGLISH);
		context.put("dateToolTimezone", TimeZone.getTimeZone("UTC"));

		StringBuffer generetedFlux = null;
		try {
			Template t = velocityEngine.getTemplate(templatePath, Constante.CHARSET);
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			generetedFlux = writer.getBuffer();
		} catch (ResourceNotFoundException e) {
			LOGGER.info(configurationLogMessageService.getDefautErrorMessage("TS.GF.RNFE"));
			throw new BusinessException(configurationLogMessageService.getDefautErrorMessage("TS.GF.RNFE"), e);
		} catch (ParseErrorException e) {
			LOGGER.info(configurationLogMessageService.getDefautErrorMessage("TS.GF.PEE"));
			throw new BusinessException(configurationLogMessageService.getDefautErrorMessage("TS.GF.PEE"), e);
		}

		return generetedFlux;
	}

	@Override
	public String getTemplate(String resource, ResourceType resourceType, Version version) throws BusinessException {
		return this.templateUtils.resolveTemplateVersionInTermsOf(resource, resourceType, version);
	}

}
