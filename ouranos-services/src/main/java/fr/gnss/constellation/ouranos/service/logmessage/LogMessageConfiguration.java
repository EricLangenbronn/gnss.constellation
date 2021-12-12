package fr.gnss.constellation.ouranos.service.logmessage;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:log-message.properties"),
        @PropertySource(value = "file:config/log-message.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${OURANOS_DIR_CONF}/log-message.properties", ignoreResourceNotFound = true)})
public class LogMessageConfiguration {
}
