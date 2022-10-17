package estoque;

import estoque.dao.ProdutoDao;
import estoque.dao.ProdutoDaoMock;
import processador.protocolo.Processador;

import java.util.AbstractCollection;
import java.util.Scanner;

public class TesteMain {

	/**
	 * Simula o navegador.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		
		/*
		 * Casos possiveis:
		 * /controlador/metodo
		 * /produto/lista
		 * /produto/filtra?nome=Produto 2&marca=Marca 1
		 */
		
		try (Scanner s = new Scanner(System.in)) {
			String url = s.nextLine();
			
			Processador processador = new Processador("estoque.controle.");
			processador.registra(ProdutoDao.class, ProdutoDaoMock.class);

			while (!url.equals("exit")) {
				Object response = processador.executa(url);
				
				System.out.println("Response: " + response);
				
				url = s.nextLine();
			}
		}

	}

}
