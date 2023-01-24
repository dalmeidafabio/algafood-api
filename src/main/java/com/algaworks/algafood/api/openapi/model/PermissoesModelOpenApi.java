package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelOpenApi {

	private PermissaoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("PermissoesEmbeddedModel")
	@Data
	public class PermissaoEmbeddedModelOpenApi {
		
		private List<PermissaoModel> permissoes;
		
	}		
	
}
