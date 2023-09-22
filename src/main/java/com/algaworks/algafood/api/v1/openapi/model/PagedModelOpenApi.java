package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi<T> {
	
	private List<T> content;
	
	private Long size;
	
	private Long totalElements;
	
	private Long totalPages;
	
	private Long number;

}
