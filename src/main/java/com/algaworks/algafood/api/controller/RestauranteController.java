package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.dto.RestauranteModel;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private SmartValidator validator;
	
	@GetMapping
	public List<Restaurante> listar(){
		
		return restauranteRepository.findAll();
	}
	
	@GetMapping("{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		RestauranteModel restauranteModel = null; //conversão do model em DTO
		
		return restauranteModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	@PutMapping("{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, 
			@RequestBody @Valid Restaurante restaurante) {

		try {
				Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

				return cadastroRestaurante.salvar(restauranteAtual);
			} catch (CozinhaNaoEncontradaException e) {
				throw new NegocioException(e.getMessage());
			}	
	}
	
	@DeleteMapping("{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}	
}
