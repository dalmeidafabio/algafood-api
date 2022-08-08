package com.algaworks.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //Já habilita 100%
		.allowedMethods("*"); //Todos os métods
		
//		registry.addMapping("/**")
//			.allowedOrigins("http://...") //Para espeficar o endereço
//			.allowedMethods("*") //Para especificar os métodos
//			.maxAge(30); // Para definir o tempo do cache
	}
}
