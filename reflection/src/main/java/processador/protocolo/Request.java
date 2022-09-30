package processador.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String nomeController;
	private String nomeMetodo;
	private Map<String, Object> queryParams;
	
	public Request(String url) {
		/*
		 * Casos possíveis:
		 * /controlador/metodo
		 * /controlador/metodo?param1=valor&param2=valor
		 * /produto/filtra?nome=valor&marca=valor
		 */
		String [] partesURL = url.replaceFirst("/","")
				.split("[?]");

		String [] controleEMetodo = partesURL[0].split("/");

		char primeiraLetra = Character.toUpperCase(controleEMetodo[0].charAt(0));
		this.nomeController = primeiraLetra + controleEMetodo[0].substring(1) + "Controller";

		this.nomeMetodo = controleEMetodo[1];

		this.queryParams = partesURL.length > 1 //se a url passada possuir parâmetros, ou seja, se ela for divida em mais de uma parte...
				? new QueryParamsBuilder().comParametros(partesURL[1]).build() //pega os parâmetros da url
				: new HashMap<String, Object>() ; //se a url nao possuir parametros, só retorna um novo Map
	}

	public String getNomeController(){
		return this.nomeController;
	}

	public String getNomeMetodo(){
		return this.nomeMetodo;
	}

	public Map<String,Object> getQueryParams(){
		return this.queryParams;
	}

}
