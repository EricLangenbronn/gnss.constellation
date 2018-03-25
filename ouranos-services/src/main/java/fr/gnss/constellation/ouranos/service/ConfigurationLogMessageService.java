package fr.gnss.constellation.ouranos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan
@PropertySources({ @PropertySource("classpath:log-message.properties"),
		@PropertySource(value = "file:config/log-message.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${OURANOS_REST_API_DIR_CONF}/log-message.properties", ignoreResourceNotFound = true), })
@Service("configurationErrorMessage")
public class ConfigurationLogMessageService implements IConfigurationLogMessageService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationLogMessageService.class);

	// -------------------- Services --------------------

	@Autowired
	private Environment environment;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String getDefautErrorMessage(String code) {
		return environment.getProperty(code);
	}
}
