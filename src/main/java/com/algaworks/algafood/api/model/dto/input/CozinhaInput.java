package com.algaworks.algafood.api.model.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

	@NotBlank
	private String nome;
	
}
