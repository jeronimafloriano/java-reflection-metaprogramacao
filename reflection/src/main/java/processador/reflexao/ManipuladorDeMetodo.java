package processador.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorDeMetodo {

    private Object instancia;
    private Method metodo;
    private Map<String, Object> params;

    private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

    public ManipuladorDeMetodo(Object instancia, Method metodo, Map<String, Object> params){
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }

    public Object invoca(){

        List<Object> parametros = new ArrayList<>();
        Stream.of(metodo.getParameters())
                .forEach(p -> parametros.add(params.get(p.getName()))); //pega cada parametro do método e insere na lista criada pegando pelo nome do método

        try {
            return this.metodo.invoke(instancia, parametros.toArray());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } catch (InvocationTargetException e) {

            if (tratamentoExcecao != null) {
                return tratamentoExcecao.apply(metodo, e);
            }

            e.printStackTrace();
            throw new RuntimeException("Erro dentro do método!", e);
        }
    }


    public ManipuladorDeMetodo comTratamentoDeExcecao(BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
        this.tratamentoExcecao = tratamentoExcecao;
        return this;
    }
}
