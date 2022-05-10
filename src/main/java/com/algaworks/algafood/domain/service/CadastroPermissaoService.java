package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	private static final String MSG_PERMISSAO_EM_USO = "Permissão de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	@Transactional
	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
			permissaoRepository.flush();
		} catch (EmptyResultDataAccessException  e) {
			throw new PermissaoNaoEncontradoException(permissaoId);
		} catch (DataIntegrityViolationException  e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_PERMISSAO_EM_USO, permissaoId));
		}
	}
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
	}	
}
