package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao", Permissao.class)
				.getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Long permissaoId) {
		Permissao permissao = buscar(permissaoId);
		
		if(permissao == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		
		manager.remove(permissao);
	}

}
