package fr.gnss.constellation.ouranos.api.config;

import fr.gnss.constellation.ouranos.api.filter.LogginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogginInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET").allowedHeaders("X-Requested-With",
                "X-Codingpedia", "Cache-Control", "Content-Type", "Origin", "Authorization", "Accept", "Client-Security-Token", "Accept-Encoding");
    }
}