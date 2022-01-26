package fr.gnss.constellation.ouranos.api.config;

import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "fr.gnss.constellation.ouranos.api")
@Import({OuranosConfiguration.class})
public class OuranosRestApplicationConfig {

}
