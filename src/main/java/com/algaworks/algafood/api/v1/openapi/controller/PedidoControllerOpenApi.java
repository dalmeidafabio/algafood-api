package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos", description = "Gerencia os Pedidos")
public interface PedidoControllerOpenApi {

	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);
    
    PedidoModel adicionar(
            PedidoInput pedidoInput);
    
    PedidoModel buscar(
            String codigoPedido);   
}