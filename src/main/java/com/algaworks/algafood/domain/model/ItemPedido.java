package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal precoUnitario = BigDecimal.ZERO;
	private BigDecimal precoTotal = BigDecimal.ZERO;
	private Integer quantidade = 0;
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	public void calcularPrecoTotal() {
	    BigDecimal precoUnitario = this.getPrecoUnitario();
	    Integer quantidade = this.getQuantidade();

	    if (precoUnitario == null) {
	        precoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}	
}
