package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes", description = "Gerencia os Restaurantes")
public interface RestauranteControllerOpenApi {

//		@JsonView(RestauranteView.Resumo.class)
	CollectionModel<RestauranteBasicoModel> listar();

	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();    
    
    RestauranteModel buscar(
            Long restauranteId);
    
    RestauranteModel adicionar(
            RestauranteInput restauranteInput);
    
    RestauranteModel atualizar(
            Long restauranteId,
            RestauranteInput restauranteInput);
    
    ResponseEntity<Void> ativar(
            Long restauranteId);
    
    ResponseEntity<Void> inativar(
            Long restauranteId);
    
    void ativarMultiplos(
            List<Long> restauranteIds);
    
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
    })
    void inativarMultiplos(
            List<Long> restauranteIds);

    ResponseEntity<Void> abrir(
            Long restauranteId);
    
    ResponseEntity<Void> fechar(
            Long restauranteId);

}