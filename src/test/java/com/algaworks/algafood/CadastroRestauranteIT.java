package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private Restaurante restauranteBrasileiro;
	
    private String jsonRestauranteCorreto;
    private String jsonRestauranteSemFrete;
    private String jsonRestauranteSemCozinha;
    private String jsonRestauranteComCozinhaInexistente;
	
    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	
	private static final Integer RESTAURANTE_ID_INEXISTENTE = Integer.MIN_VALUE;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
	    jsonRestauranteCorreto = ResourceUtils.getContentFromResource("/json/correto/restaurante-comida-mineira.json");
	    jsonRestauranteSemFrete = ResourceUtils.getContentFromResource("/json/incorreto/restaurante-sem-taxa-frete.json");
	    jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource("/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json");
	    jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource("/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	private void prepararDados() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Japonesa");
		cozinhaRepository.save(cozinha);
		
		restauranteBrasileiro = new Restaurante();
		restauranteBrasileiro.setNome("Salomon Sushi");
		restauranteBrasileiro.setCozinha(cozinha);
		restauranteBrasileiro.setTaxaFrete(new BigDecimal(10));
		restauranteRepository.save(restauranteBrasileiro);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
		given()
			.pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.assertThat()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoTaxaFreteNaoForInformada() {
		given()
			.body(jsonRestauranteSemFrete)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
		given()
			.body(jsonRestauranteSemCozinha)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}	
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
		given()
			.body(jsonRestauranteCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatusOK_QuandoConsultarRestauranteExistente() {
		given()
			.pathParam("restauranteId", restauranteBrasileiro.getId())
			.contentType(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.assertThat()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCozinhaInexistente() {
		given()
		.body(jsonRestauranteComCozinhaInexistente)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.post()
	.then()
		.assertThat()
		.statusCode(HttpStatus.BAD_REQUEST.value())
        .body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
	}
	
    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
        given()
            .pathParam("restauranteId", restauranteBrasileiro.getId())
            .accept(ContentType.JSON)
        .when()
            .get("/{restauranteId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo(restauranteBrasileiro.getNome()));
    }	
	
}
