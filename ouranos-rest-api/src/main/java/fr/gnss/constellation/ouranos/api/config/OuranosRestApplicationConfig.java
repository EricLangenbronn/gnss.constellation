package fr.gnss.constellation.ouranos.api.config;

import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "fr.gnss.constellation.ouranos.api")
@PropertySource("classpath:ouranos-rest-api.properties")
@Import({OuranosConfiguration.class})
public class OuranosRestApplicationConfig {

}
