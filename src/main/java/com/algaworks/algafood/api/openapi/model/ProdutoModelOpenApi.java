package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ProdutosModel")
@Data
public class ProdutoModelOpenApi {

	private ProdutoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("ProdutosEmbeddedModel")
	@Data
	public class ProdutoEmbeddedModelOpenApi {
		
		private List<ProdutoModel> produtos;
		
	}		
	
	
}