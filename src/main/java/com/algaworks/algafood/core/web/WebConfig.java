package com.algaworks.algafood.core.web;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private ApiDeprecationHandler apiDeprecationHandler;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //Já habilita 100%
		.allowedMethods("*"); //Todos os métodos
		
//		registry.addMapping("/**")
//			.allowedOrigins("http://...") //Para espeficar o endereço
//			.allowedMethods("*") //Para especificar os métodos
//			.maxAge(30); // Para definir o tempo do cache
	}
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiDeprecationHandler);
	}
}
