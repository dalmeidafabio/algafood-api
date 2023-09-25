package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados", description = "Gerencia os Estados")
public interface EstadoControllerOpenApi {

    CollectionModel<EstadoModel> listar();

    EstadoModel buscar(
            Long estadoId);

    EstadoModel adicionar(
            EstadoInput estadoInput);

    EstadoModel atualizar(
            Long estadoId,
            EstadoInput estadoInput);

    void remover(
            Long estadoId);
}      