package fr.gnss.constellation.ouranos.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "fr.gnss.constellation.ouranos.api")
@ImportResource("classpath:/module/ouranos-services.xml")
@PropertySource("classpath:application.properties")
public class WebAppConfig {

}
