package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	
	@ApiResponses({
		@ApiResponse(responseCode = "404", 
					description = "Grupo não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", 
					description = "ID do Grupo é inválido", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Busca um grupo por ID.")
	GrupoModel buscar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Grupo criado", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Grupo não encontrado", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Cadastra um Grupo.")	
	GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
			GrupoInput grupoInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Grupo atualizado", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Grupo não encontrado", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Cadastra um Grupo.")	
	GrupoModel atualizar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId,
			
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", 
				required = true)
			GrupoInput grupoInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", 
					description = "Grupo excluído", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Grupo não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Exclui um grupo por ID.")	
	void remover(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);

}
