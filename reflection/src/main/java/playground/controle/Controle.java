package playground.controle;

import java.util.List;

public class Controle {

	public Controle() {
	}

	public Controle(String s){}

	private Controle(String s, String t){}

	private List<String> lista = List.of("item 1", "item 2", "item 3");
	
	public List<String> getLista() {
		return lista;
	}

	public void metodoControle2(String p1){
		System.out.println("Chamando metodoControle2 que recebe uma String como parâmetro.");
		System.out.println("Parâmetro obtido p1: " + p1);
	}

	public void metodoControle2(String p1, String p2){
		System.out.println("Chamando metodoControle2 que recebe duas Strings como parâmetros.");
		System.out.println("Parâmetro obtido p1: " + p1);
		System.out.println("Parâmetro obtido p2: " + p2);
	}

	public void metodoControle2(String p1, Integer p2){
		System.out.println("Chamando metodoControle2 que recebe uma String e um Integer como parâmetros.");
		System.out.println("Parâmetro obtido p1: " + p1);
		System.out.println("Parâmetro obtido p2: " + p2);
	}

	private void metodoControle1(){

	}
}
