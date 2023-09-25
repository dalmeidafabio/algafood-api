package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes - Produtos - Fotos dos Produtos", description = "Gerencia as Fotos dos Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

    FotoProdutoModel atualizarFoto(
            Long restauranteId,
            Long produtoId,
            FotoProdutoInput fotoProdutoInput,
            MultipartFile arquivo) throws IOException;

    void excluir(
            Long restauranteId,
            Long produtoId);

    FotoProdutoModel buscar(
            Long restauranteId,
            Long produtoId);

    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader) 
            throws HttpMediaTypeNotAcceptableException;
}