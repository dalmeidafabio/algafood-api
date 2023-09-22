package com.algaworks.algafood.api.v1.openapi.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinksModelOpenApi {
	
	private LinkModel rel;
	
	@Setter
	@Getter
	private class LinkModel {
		
		private String href;
		private boolean templated;
		
	}

}
