package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os estados")
    CollectionModel<EstadoModel> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(mediaType = "application/json",  
				schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "400", description = "Estado não encontrado", content = @Content(mediaType = "application/json",  
		schema = @Schema(implementation = Problem.class)))
    })
    EstadoModel buscar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long estadoId);

    @ApiOperation("Cadastra um estado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Estado cadastrado"),
    })
    EstadoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
            EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estado atualizado"),
        @ApiResponse(responseCode = "400", description = "Estado não encontrado", content = @Content(mediaType = "application/json",  
		schema = @Schema(implementation = Problem.class)))
    })
    EstadoModel atualizar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long estadoId,
            
            @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true)
            EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Estado excluído"),
        @ApiResponse(responseCode = "400", description = "Estado não encontrado", content = @Content(mediaType = "application/json",  
		schema = @Schema(implementation = Problem.class)))
    })
    void remover(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long estadoId);
}      