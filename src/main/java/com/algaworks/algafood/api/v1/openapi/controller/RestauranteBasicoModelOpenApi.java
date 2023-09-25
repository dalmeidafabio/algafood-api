package com.algaworks.algafood.api.v1.openapi.controller;

import java.math.BigDecimal;

import com.algaworks.algafood.api.v1.model.CozinhaModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes", description = "Gerencia os Restaurantes")
public class RestauranteBasicoModelOpenApi {

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;
	
}
