package br.ce.wcaquino.curso_rest;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTeste {

	@Test
	public void testOlaMundo() {

		// fazendo uma requisi��o para a API https://restapi.wcaquino.me/ola
		RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

		/*
		 * Servidor vai retornar uma resposta(response) eh preciso criar um objeto do
		 * tipo Response, para receber a resposta do servidor
		 * 
		 */
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

		/*
		 * Neste exemplo o que vamos solicitar como response � o corpo da
		 * requisi��o(body) Abaixo duas formas de imprimir na tela o response
		 */
		System.out.println(response.getBody().asString());
		System.out.println(response.statusCode());

		/*
		 * Verifca��es/asser��es Criei uma l�gica para primeiro verificar se p body da
		 * requisi��o � igual a "Ola Mundo!" A segunda asser��o verifica se o status
		 * code da requisi��o � igual a 200
		 */
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);

		Assert.assertTrue("O status code deveria ser 200", response.statusCode() == 200);

		/*
		 * Outra forma de verificar o response da requisi��o Na linha abaixo devemos
		 * passar o parametro que esperamos e o assertEquals verifica se � igual ao
		 * recebido pela requisi��o
		 */
		Assert.assertEquals(200, response.statusCode());

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

	}

	@Test
	public void devoConhecerOutrasFormasRestAssured() {

		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

		/*
		 * As linhas acima poderiam ser escritas dessa forma resumida
		 * RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);
		 */

		/*
		 * Tamb�m � possivel resumir ainda mais a linha acima, porque a partir do
		 * RestAssured estamos utilizando o metodo statico get, podemos fazer este
		 * import statico para nem precisar utilizar o RestAssured na frente do get por
		 * exemplo.
		 */
		get("https://restapi.wcaquino.me/ola").then().statusCode(200);

		// Metodo Fluente utilizando uma sintaxe semelhante ao Gherkin
		given()
				// Pr�-condi�oes
				.when()
				// a�ao
				.get("https://restapi.wcaquino.me/ola").then()
				// Assertivas
				.statusCode(200);
	}

	@Test
	public void devoConhecerMatchersHamcrest() {
		/*
		 * Os "Matchers" do Hamcrest s�o objetos que realizam correspond�ncia entre um
		 * valor de teste (o valor que voc� est� testando em seu c�digo) e um valor
		 * esperado (o valor que voc� deseja comparar). Eles permitem que voc� expresse
		 * asser��es de uma forma muito leg�vel e fluente. Algumas verifica��es que
		 * podemos fazer com assertThat
		 */

		// Matcher is para verificar igualdade

		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));

		// Matcher isA para verificar o tipo
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128.5, Matchers.isA(Double.class));

		// Pelo comando Matchers greaterThan podemos tabem verificar o tamanho
		Assert.assertThat(128d, Matchers.greaterThan(120d));

		// Pelo comando Matchers lessThan podemos tabem verificar o tamanho
		Assert.assertThat(128d, Matchers.lessThan(130d));

		List<Integer> impares = Arrays.asList(1, 2, 3, 4, 5);
		Assert.assertThat(impares, Matchers.hasSize(5));
		Assert.assertThat(impares, Matchers.contains(1, 2, 3, 4, 5));
		Assert.assertThat(impares, Matchers.containsInAnyOrder(1, 2, 4, 5, 3));
		Assert.assertThat(impares, Matchers.hasItem(5));
		Assert.assertThat(impares, Matchers.hasItems(1, 2));

		// Link documentacao de todos os matchers disponiveis
		// https://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
	}

	@Test
	public void devoValidarBody() {

		given()	 
			//Pr�-condi�oes
		.when()
			//a�ao
			.get("https://restapi.wcaquino.me/ola")
		.then()
			//Assertivas
			.statusCode(200)
			//.body("Ola Mundo!", Matchers.is("Ola Mundo!"))
			.body(Matchers.containsString("Ola"))
			;

	}
}
