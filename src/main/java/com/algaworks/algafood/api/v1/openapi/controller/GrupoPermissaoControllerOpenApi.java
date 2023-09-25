package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos - Permissões", description = "Gerencia os Grupos de Permissões de Acesso")
public interface GrupoPermissaoControllerOpenApi {
    
    CollectionModel<PermissaoModel> listar(
            Long grupoId);

    ResponseEntity<Void> desassociar(
            Long grupoId,
            Long permissaoId);

    ResponseEntity<Void> associar(
            Long grupoId,
            Long permissaoId);
}      