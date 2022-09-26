package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@ApiOperation("Lista as cidades.")
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	
	@ApiResponses({
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", 
					description = "ID da cidade é inválido", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})	
	@ApiOperation("Busca uma cidade por ID.")
	@GetMapping("{cidadeId}")
	public CidadeModel buscar(
			@ApiParam(value = "ID de uma cidade.", example = "1")
			@PathVariable Long cidadeId) {
		return cidadeModelAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Cidade criada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Cadastra uma cidade.")
	@PostMapping
	public CidadeModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade.") 
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = cadastroCidade.salvar(cidade);
			
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}		
	}	
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", 
					description = "Cidade atualizada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Atualiza uma cidade por ID.")
	@PutMapping("{cidadeId}")
	public CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade.", example = "1") @PathVariable Long cidadeId, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados.") @RequestBody 
			@Valid CidadeInput cidadeInput) {		
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cadastroCidade.salvar(cidadeAtual);
			
			return cidadeModelAssembler.toModel(cidadeAtual);
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", 
					description = "Cidade excluída", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
					description = "Cidade não encontrada", 
					content = @Content(mediaType = "application/json",  
									schema = @Schema(implementation = Problem.class)))
	})		
	@ApiOperation("Exclui uma cidade por ID.")
	@DeleteMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@ApiParam(value = "ID de uma cidade.", example = "1") 
			@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
