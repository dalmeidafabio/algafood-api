package com.algaworks.algafood.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiaria {

	private Date data;
	private Long totalVenda;
	private BigDecimal totalFaturado;
	
	//java.sql.Date para não dar erro no jasperReports
	public VendaDiaria(java.sql.Date data, Long totalVenda, BigDecimal totalFaturado) {
		this.data = new Date(data.getTime());
		this.totalVenda = totalVenda;
		this.totalFaturado = totalFaturado;
	}
	
}
