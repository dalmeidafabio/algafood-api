package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import lombok.Data;

@Data
public class PermissoesModelOpenApi {

	private PermissaoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@Data
	public class PermissaoEmbeddedModelOpenApi {
		
		private List<PermissaoModel> permissoes;
		
	}		
	
}
