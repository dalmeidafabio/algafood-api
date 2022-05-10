package com.algaworks.algafood.api.model.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
}
