package fr.gnss.constellation.ouranos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:ouranos.properties"),
        @PropertySource(value = "file:config/${OURANOS_FILE_CONF}", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${OURANOS_DIR_CONF}/${OURANOS_FILE_CONF}", ignoreResourceNotFound = true)})
@ComponentScan(basePackages = "fr.gnss.constellation.ouranos")
public class OuranosConfiguration {

}
