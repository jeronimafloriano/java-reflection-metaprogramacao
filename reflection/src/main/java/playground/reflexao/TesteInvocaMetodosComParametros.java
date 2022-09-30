package playground.reflexao;

import playground.controle.Controle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesteInvocaMetodosComParametros {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       Class<Controle> classControle =  Controle.class;
       Constructor<Controle> constructor  = classControle.getDeclaredConstructor();
       Controle controle = constructor.newInstance();

        Method m = controle.getClass().getDeclaredMethod("metodoControle2", String.class);
        Object retorno = m.invoke(controle, "teste");
        System.out.println();

        Method m2 = classControle.getDeclaredMethod("metodoControle2", String.class, String.class);
        Object retorno2 = m2.invoke(controle, "João", "Maria");
        System.out.println();

        Method m3 = classControle.getDeclaredMethod("metodoControle2", String.class, Integer.class);
        Object retorno3 = m3.invoke(controle, "João", 123);
        System.out.println();

        System.out.println(retorno);
        System.out.println(retorno2);
        System.out.println(retorno3);
    }
}
