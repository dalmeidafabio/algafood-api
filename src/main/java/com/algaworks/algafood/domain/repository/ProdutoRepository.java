package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	
	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId, 
			@Param("produto") Long produtoId);
	
	List<Produto> findTodosByRestaurante(Restaurante restaurante);

	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findTodosAtivosByRestaurante(Restaurante restaurante);
}
