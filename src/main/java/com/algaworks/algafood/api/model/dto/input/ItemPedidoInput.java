package com.algaworks.algafood.api.model.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
    private Long produtoId;
	
	@PositiveOrZero
	@NotNull
    private Integer quantidade;

    private String observacao;     
    
}
