package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNaoEncontradoException(Long permissaoId) {
		this(String.format("Não existe um cadastro de Permissão com código %d", permissaoId));
	}	
}
