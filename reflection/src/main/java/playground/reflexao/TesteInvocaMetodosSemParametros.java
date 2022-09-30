package playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesteInvocaMetodosSemParametros {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> classe = Class.forName("playground.controle.SubControle");

        System.out.println("getMethods: ");
        for (Method m : classe.getMethods()) { //retorna métodos publicos da classe e classe mãe/interfaces
            System.out.println(m);
        }

        System.out.println();
        System.out.println("getDeclaredMethods: ");
        for(Method m : classe.getDeclaredMethods()){ //retorna métodos publicos e privados da classe
            System.out.println(m);
        }

        System.out.println();

        Constructor<?> construtor = classe.getDeclaredConstructor(); //pegando o construtor
        construtor.setAccessible(true);//construtor é privado, liberando o acesso
        Object subControle = construtor.newInstance(); //criando a instância

        Method m = classe.getDeclaredMethod("metodoSubControle2"); //chamando o método privado
        m.setAccessible(true);//método é privado, liberando o acesso
        Object retornoMetodo = m.invoke(subControle);
        System.out.println(retornoMetodo);

    }
}
