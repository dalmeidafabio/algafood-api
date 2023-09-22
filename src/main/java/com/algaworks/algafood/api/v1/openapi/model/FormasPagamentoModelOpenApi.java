package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import lombok.Data;

@Data
public class FormasPagamentoModelOpenApi {
	
	private FormaPagamentoModelOpenApi _embedded;
	private Links _links;
	
	@Data
	public class FormaPagamentoModelOpenApi {
		
		private List<FormaPagamentoModel> formasPagamento;
		
	}	

}
