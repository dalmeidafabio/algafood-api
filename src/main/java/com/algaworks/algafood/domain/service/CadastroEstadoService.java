package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public List<Estado> listar(){
		return estadoRepository.listar();
	}
	
	public Estado buscar(Long estadoId) {
		return estadoRepository.buscar(estadoId);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.remover(estadoId);
		} catch (EmptyResultDataAccessException  e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Estado com código %d", estadoId));
		} catch (DataIntegrityViolationException  e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removida, pois está em uso.", estadoId));
		}
	}	
}
