package com.algaworks.algafood.api.controller.openapi;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	public GrupoModel buscar(@PathVariable Long grupoId);
	
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
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput);
	
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
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput);
	
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
	public void remover(@PathVariable Long grupoId);

}
