package fr.gnss.constellation.ouranos.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Configuration
@PropertySources({ @PropertySource("classpath:ouranos-rest-api.properties"),
		@PropertySource(value = "file:config/${OURANOS_REST_API_FILE_CONF}", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${OURANOS_REST_API_DIR_CONF}/${OURANOS_REST_API_FILE_CONF}", ignoreResourceNotFound = true) })
@ComponentScan(basePackages = "fr.gnss.constellation.ouranos.service")
@Service("ouranosConfigurationService")
public class ConfigurationService {

}
