package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes - Produtos do Restaurante", description = "Gerencia os Produtos dos Restaurantes")
public interface RestauranteProdutoControllerOpenApi {

    CollectionModel<ProdutoModel> listar(
            Long restauranteId,
            Boolean incluirInativos);

    ProdutoModel buscar(
            Long restauranteId,
            Long produtoId);

    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(mediaType = "application/json",  
		schema = @Schema(implementation = Problem.class)))
    })
    ProdutoModel adicionar(
            Long restauranteId,
            ProdutoInput produtoInput);

    ProdutoModel atualizar(
            Long restauranteId,
            Long produtoId,
            ProdutoInput produtoInput);
}