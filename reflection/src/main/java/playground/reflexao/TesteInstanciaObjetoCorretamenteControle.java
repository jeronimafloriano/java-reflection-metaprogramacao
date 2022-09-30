package playground.reflexao;

import playground.controle.Controle;

import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjetoCorretamenteControle {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class<Controle> controle = Controle.class;
        //Controle classControle = controle.newInstance(); //joga exceção do construtor sem avisar

        Controle classControle2 = controle.getDeclaredConstructor().newInstance(); //avisa antes

    }
}
