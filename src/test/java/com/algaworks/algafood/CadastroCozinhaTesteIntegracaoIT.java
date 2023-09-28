package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import jakarta.validation.ConstraintViolationException;

///ESSA CLASSE FOI REMOVIDA DO PROJETO DO CURSO - DEIXEI APENAS PARA CONSULTA
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CadastroCozinhaTesteIntegracaoIT {
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}	
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
	   Cozinha novaCozinha = new Cozinha();
	   novaCozinha.setNome(null);
	   
	   ConstraintViolationException erroEsperado =
	      Assertions.assertThrows(ConstraintViolationException.class, () -> {
	         cadastroCozinha.salvar(novaCozinha);
	      });
	   
	   assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		Long cozinhaId = 1L;   
		
		EntidadeEmUsoException erroEsperado =
			      Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			    	  cadastroCozinha.excluir(cozinhaId);
			      });
	   
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Long cozinhaId = Long.MAX_VALUE;   
	
		CozinhaNaoEncontradaException erroEsperado =
			      Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
			    	  cadastroCozinha.excluir(cozinhaId);
			      });
	   
		assertThat(erroEsperado).isNotNull();
	}
}