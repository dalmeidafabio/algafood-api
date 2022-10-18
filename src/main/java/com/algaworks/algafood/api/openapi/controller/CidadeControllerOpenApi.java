package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;

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
	public List<Cidade> listar();
	
	
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
	public CidadeModel buscar(@ApiParam(name = "ID de uma cidade") Long cidadeId);
	
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
	public CidadeModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade.") 
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
	public CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade.", example = "1") Long cidadeId, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados.")  
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
	public void remover(
			@ApiParam(value = "ID de uma cidade.", example = "1") 
			Long cidadeId);
	
}
