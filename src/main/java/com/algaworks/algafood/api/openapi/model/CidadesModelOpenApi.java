package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.CidadeModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("Cidades Model")
@Data
public class CidadesModelOpenApi {
	
	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("Cidades Embedded Model")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		
		private List<CidadeModel> cidades;
		
	}

}
