package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

//Poderia ser CozinhasRepresentationModel
@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {

	@NonNull //para dizer que a propriedade é obrigatório e, assim, o lombok gere o construtor com cozinhas
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false) //para retirar o embrulho realizado na lista
	private List<Cozinha> cozinhas;
	
}
