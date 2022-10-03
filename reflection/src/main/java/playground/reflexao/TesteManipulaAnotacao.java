package playground.reflexao;

import estoque.anotacao.NomeTagXml;
import estoque.modelo.Produto;

public class TesteManipulaAnotacao {

    public static void main(String[] args) {
        Produto produto = new Produto("Teclado", 50.0,"Dell");
        Class<?> classe = produto.getClass();

        System.out.println("Método getAnnotations: " + classe.getAnnotations());
        System.out.println("Método getDeclaredAnnotations: " + classe.getDeclaredAnnotations());

        System.out.println("Método getDeclaredAnnotation: " + classe.getDeclaredAnnotation(NomeTagXml.class));
        //retorna apenas anotações da classe em questão, sem considerar anotações feitas nas classes estendidas.


    }
}
