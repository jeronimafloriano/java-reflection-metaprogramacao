package playground.reflexao;

import estoque.modelo.Produto;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TesteManipulaAtributos {

    /*
        <produto>
            <nome>Produto 1</nome>
            <valor>20.0</valor>
            <marca>Marca 1</marca>
        </produto>
     */

    public static void main(String[] args) throws IllegalAccessException {
        Produto produto = new Produto("Xobx", 2.500, "Sony");
        Class<?> classe = produto.getClass();

        Arrays.stream(classe.getFields()).forEach(System.out::println);

        for (Field atributo : classe.getDeclaredFields()){
            atributo.setAccessible(true);

            System.out.println("Atributo: " + atributo.getName()
                    + " - Valor: " + atributo.get(produto));
        }
    }
}
