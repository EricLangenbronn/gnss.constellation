package fr.gnss.constellation.ouranos.service.template;

import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.thymeleaf.exceptions.TemplateOutputException;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractTemplateService implements ITemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTemplateService.class);

    // -------------------- Services --------------------

    private final TemplateEngine templateEngine;

    private final IConfigurationLogMessageService configurationLogMessageService;

    // -------------------- Propriétés de la classe --------------------

    private TemplateUtils templateUtils = new TemplateUtils();

    public AbstractTemplateService(TemplateEngine templateEngine, IConfigurationLogMessageService configurationLogMessageService) {
        this.templateEngine = templateEngine;
        this.configurationLogMessageService = configurationLogMessageService;
    }

    // -------------------- Methodes de l'interface --------------------

    @Override
    public StringBuffer generateFlux(String templatePath, Map<String, Object> informations) {
        Context context = new Context();
        for (Entry<String, Object> info : informations.entrySet()) {
            context.setVariable(info.getKey(), info.getValue());
        }

        context.setVariable("dateFormat", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        StringBuffer generetedFlux = null;
        try {
            templateEngine.process(templatePath, context);
            StringWriter writer = new StringWriter();
            templateEngine.process(templatePath, context, writer);
            generetedFlux = writer.getBuffer();
        } catch (TemplateOutputException e) {
            throw new RuntimeException(configurationLogMessageService.getDefautErrorMessage("TS.GF.RNFE"), e);
        } catch (TemplateEngineException e) {
            throw new RuntimeException(configurationLogMessageService.getDefautErrorMessage("TS.GF.PEE"), e);
        } catch (RuntimeException e) {
            throw new RuntimeException(configurationLogMessageService.getDefautErrorMessage("TS.GF.PEE"), e);
        }

        return generetedFlux;
    }

    @Override
    public String getTemplate(String resource, ResourceType resourceType, Version version) {
        return this.templateUtils.resolveTemplateVersionInTermsOf(resource, resourceType, version);
    }

}
