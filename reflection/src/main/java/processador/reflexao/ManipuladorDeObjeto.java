package processador.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorDeObjeto {

    private Object instancia;
    public ManipuladorDeObjeto(Object instancia){
        this.instancia = instancia;
    }

    public ManipuladorDeMetodo getMetodo(String nomeMetodo, Map<String, Object> params){

        //controlador/filtra?nome=Produto 2&marca=Marca 1

        Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());

        Method metodoSelecionado = metodos.filter(metodo ->
                metodo.getName().equals(nomeMetodo) //verifica se o nome informado é igual o nome do método
                && metodo.getParameterCount() == params.values().size() //verifica se a quantidade de parametros passados é igual a quantidade de parâmetros do método
                && Stream.of(metodo.getParameters())
                        .allMatch(arg ->
                                params.keySet().contains(arg.getName()) //verifica se o nome dos parâmetros passados é igual ao nome dos parâmetros do método
                                && params.get(arg.getName()).getClass().equals(arg.getType()))) //verificando se o tipo dos parâmetros passados é igual ao tipo dos parâmetros do método
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Método não encontrado."));



            return new ManipuladorDeMetodo(instancia, metodoSelecionado, params);
    }


}
