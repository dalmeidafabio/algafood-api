package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas", description = "Gerencia Cozinhas")
public interface CozinhaControllerOpenApi {

	@PageableParameter
	PagedModel<CozinhaModel> listar(@Parameter(hidden = true) Pageable pageable);

	CozinhaModel buscar(Long cozinhaId);

	CozinhaModel adicionar(CozinhaInput cozinhaInput);

	CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	ResponseEntity<Void> remover(Long cozinhaId);
	
}