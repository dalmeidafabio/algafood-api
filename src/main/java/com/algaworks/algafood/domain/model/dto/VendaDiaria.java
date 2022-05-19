package com.algaworks.algafood.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VendaDiaria {

	private LocalDate data;
	private Long totalVenda;
	private BigDecimal totalFaturado;
	
}