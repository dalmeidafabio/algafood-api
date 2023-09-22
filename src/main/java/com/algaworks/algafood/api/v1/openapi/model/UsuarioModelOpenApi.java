package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import lombok.Data;

@Data
public class UsuarioModelOpenApi {
	
	private UsuarioEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@Data
	public class UsuarioEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
		
	}	

}
