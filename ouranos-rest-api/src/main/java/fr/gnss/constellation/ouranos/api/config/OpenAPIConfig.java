package fr.gnss.constellation.ouranos.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI api(@Value("${info.app.version}") String buildVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo(buildVersion));
    }

    private Info apiInfo(String buildVersion) {
        return new Info()
                .title("ouranos")
                .description("Micro service")
                .version(buildVersion)
                .contact(new Contact().name("Ouranos"));
    }
}
