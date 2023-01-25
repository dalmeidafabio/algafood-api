package com.algaworks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CidadeInput")
public class CidadeInputV2 {

	@ApiModelProperty(example = "Salvador", required = true)
	@NotBlank
	private String nomeCidade;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long idEstado;
	
}
