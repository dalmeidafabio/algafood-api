package com.algaworks.algafood.core.squiggly;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {

	// ESSA BIBLIOTECA NÃO TEM ATUALIZAÇÃO DESDE 2019. NÃO FUNCIONOU BEM NO MEU PROJETO...!
	
	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper){
		Squiggly.init(objectMapper, new RequestSquigglyContextProvider());
		
		var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
		
		var filterRegistrarion = new FilterRegistrationBean<SquigglyRequestFilter>();
		
		filterRegistrarion.setFilter(new SquigglyRequestFilter());
		filterRegistrarion.setOrder(1);
		filterRegistrarion.setUrlPatterns(urlPatterns);
		
		return filterRegistrarion;
	}
}
