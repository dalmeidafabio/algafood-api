package com.algaworks.algafood.api.v1.model;

import java.util.List;

import org.springframework.hateoas.Links;

import lombok.Data;

@Data
public class EstadosModelOpenApi {
	
	private EstadoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@Data
	public class EstadoEmbeddedModelOpenApi {
		
		private List<EstadoModel> estados;
		
	}	

}
