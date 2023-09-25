package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades", description = "Gerencia as Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as Cidades")
	CollectionModel<CidadeModel> listar();
	
	@Operation(summary = "Busca uma Cidade por ID")
	CidadeModel buscar(Long cidadeId);
	
	@Operation(summary = "Cadastra uma Cidade", description = "Cadastro de uma cidade necessita de um Estado e nome válidos.")
	CidadeModel adicionar(CidadeInput cidadeInput);
	
	@Operation(summary = "Atualiza uma Cidade por ID")
	CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);
	
	@Operation(summary = "Exclui uma Cidade por ID")
	void remover(Long cidadeId);
	
}
