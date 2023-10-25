package br.ce.wcaquino.curso_rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundo {
	
	public static void main(String[] args) {
		
		//fazendo uma requisição para a API https://restapi.wcaquino.me/ola
		RestAssured.request(Method.GET,"https://restapi.wcaquino.me/ola");
		
		/*
		 * Servidor vai enviar uma resposta(response) eh preciso criar 
		 * um objeto do tipo Response, para receber a resposta do servidor
		 * 
		 */	
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
		
		/*
		 * Neste exemplo o que vamos solicitar como response é o corpo da
		 * requisição(body) Abaixo duas formas de imprimir na tela o response
		 */
		//System.out.println(response.getBody().asString().equals("Ola Mundo!"));
		//System.out.println(response.statusCode() == 200);
		
		//uma seria utilizar uma ferramenta de verificação do próprio rest assured
		ValidatableResponse validacao = response.then();
		validacao.statusCode(201);
	}

}
