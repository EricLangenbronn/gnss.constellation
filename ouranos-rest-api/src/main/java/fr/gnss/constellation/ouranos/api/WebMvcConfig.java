package fr.gnss.constellation.ouranos.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.gnss.constellation.ouranos.api.controller.filter.LogginInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogginInterceptor());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH").allowedHeaders("X-Requested-With",
				"X-Codingpedia", "Cache-Control", "Content-Type", "Origin", "Authorization", "Accept", "Client-Security-Token", "Accept-Encoding");
	}
}