package playground.reflexao;

import playground.controle.SubControle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjetoCorretamenteSubControle {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<SubControle> subControleClass = SubControle.class; //pegando a classe
        Constructor<SubControle> declaredConstructor = subControleClass.getDeclaredConstructor(Integer.class); //pegando o construtor
        Constructor<SubControle> declaredConstructor2 = subControleClass.getDeclaredConstructor(String.class);// pegando o construtor passando quais os argumentos do contrutor em questão que eu quero

        System.out.println(declaredConstructor.getTypeParameters());

        declaredConstructor2.setAccessible(true); //declarando o acesso ao construtor privado

        SubControle subControle = declaredConstructor.newInstance(1);
        System.out.println("pegando a instância do objeto com o construtor publico: " + subControle);
        System.out.println("retornando construtores publicos: " + subControleClass.getConstructors()); // retorna construtores publicos
        System.out.println("retornando construtores publicos com parâmetro: " + subControleClass.getConstructor(Integer.class));



    }
}
