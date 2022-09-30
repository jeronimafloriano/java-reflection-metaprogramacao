package playground.reflexao;

import playground.controle.Controle;

import java.io.IOException;

public class TesteInstanciaObjeto {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		// url -> /controle/lista
		
		Class<Controle> controleClasse1 = Controle.class; //1 forma
		
		Controle controle = new Controle();
		Class<? extends Controle> controleClasse2 = controle.getClass(); //1 forma
		
		Class<?> controleClasse3 =
				Class.forName("playground.controle.Controle"); //1 forma
		
		Controle objetoControle = controleClasse1.newInstance();
		
		Object outroObjetoControle = controleClasse3.newInstance();
		
		System.out.println(objetoControle instanceof Controle);
		
		System.out.println(outroObjetoControle instanceof Controle);
	}

}
