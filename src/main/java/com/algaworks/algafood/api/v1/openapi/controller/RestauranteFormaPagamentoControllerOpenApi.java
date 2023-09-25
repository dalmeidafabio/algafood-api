package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes - Formas de Pagamento", description = "Gerencia as Formas de Pagamento do Restaurante")
public interface RestauranteFormaPagamentoControllerOpenApi {
    
    CollectionModel<FormaPagamentoModel> listar(
            Long restauranteId);

    ResponseEntity<Void> desassociar(
            Long restauranteId,
            Long formaPagamentoId);

    ResponseEntity<Void> associar(
            Long restauranteId,
            Long formaPagamentoId);
}
