package fr.gnss.constellation.ouranos.service.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class TemplateConfiguration {

	@Bean
	public SpringResourceTemplateResolver getTemplateResolver(TemplateMode templateMode) {

		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		/*
		 * templateResolver.setPrefix("classpath:/templates/");
		 * templateResolver.setSuffix(".vm");
		 */
		templateResolver.setTemplateMode(templateMode);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean("jsonTemplateEngine")
	public SpringTemplateEngine getJsonTemplateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver(TemplateMode.JAVASCRIPT));
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new Java8TimeDialect());

		return templateEngine;
	}

	@Bean("xmlTemplateEngine")
	public SpringTemplateEngine getXMlTemplateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver(TemplateMode.XML));
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new Java8TimeDialect());

		return templateEngine;
	}

}
