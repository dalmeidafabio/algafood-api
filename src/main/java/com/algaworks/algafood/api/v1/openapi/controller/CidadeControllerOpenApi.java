package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades.")
	CollectionModel<CidadeModel> listar();
	
	
	@ApiResponses({
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", 
					description = "ID da cidade é inválido", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})	
	@ApiOperation("Busca uma cidade por ID.")
	CidadeModel buscar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long cidadeId);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Cidade criada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Cadastra uma cidade.")
	CidadeModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
			CidadeInput cidadeInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Cidade atualizada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Atualiza uma cidade por ID.")
	CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
			CidadeInput cidadeInput);		
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", 
					description = "Cidade excluída", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Exclui uma cidade por ID.")
	void remover(
			@ApiParam(value = "ID de uma cidade.", example = "1", required = true) 
			Long cidadeId);
	
}
