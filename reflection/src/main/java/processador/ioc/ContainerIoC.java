package processador.ioc;

import estoque.dao.ProdutoDao;
import estoque.dao.ProdutoDaoMock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;

public class ContainerIoC {

    private Map<Class<?>, Class<?>> mapaDeTiposDeClasses = new HashMap<>();
    public Object getInstancia(Class<?> tipoFonte){

        Class<?> classeTipoDestino = mapaDeTiposDeClasses.get(tipoFonte);
        if ((classeTipoDestino != null)) {
            return getInstancia(classeTipoDestino);
        }

        Stream<Constructor> construtores =
                Stream.of(tipoFonte.getDeclaredConstructors()); //pega os construtores

        Optional<Constructor> construtorPadrao =
                construtores.filter(constructor -> constructor.getParameterCount() == 0)
                        .findFirst(); //procurando o construtor padrão, com qtds parâmetros igual a 0

        try {
           if (construtorPadrao.isPresent()){

               Object instancia = construtorPadrao.get().newInstance(); //gerando a instancia com o  construtor padrão
               return instancia;
           } else {
               Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0]; //pegando o primeiro construtor com parâmetros que encontrar

               List<Object> params = new ArrayList<>(); //criando uma lista para armazenar os parametros do construtor

               for (Parameter param : construtor.getParameters()){ //iterando nos parâmetros do construtor em questão

                   Class<?> tipoDoParametro = param.getType(); //pegando o tipo do parâmetro
                   params.add(getInstancia(tipoDoParametro)); //adicionand na lista a instância do parâmetro do construtor em questão
               }
               return construtor.newInstance(params.toArray()); //retornando a instância do construtor passando a instancia dos parâmetros do construtor
           }
        } catch (InvocationTargetException | InstantiationException
                | RuntimeException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public <T, K extends T> void registra(Class<T> classeTipoFonte, Class<K> classeTipoDestino) {
        /*boolean compativel = verificaCompatibilidade(classeTipoFonte, classeTipoDestino);

        if(!compativel) throw new ClassCastException("Não é possível resolver "
                + classeTipoFonte.getName() + " para: " + classeTipoDestino.getName());

         mapaDeTiposDeClasses.put(classeTipoFonte, classeTipoDestino);*/

        ///TODO SSE CÓDIGO ACIMA E O MÉTODO 'verificaCompatibilidade' PODE SER RESUMIDO EM:
        mapaDeTiposDeClasses.put(classeTipoFonte, classeTipoDestino);
    }

    /*private boolean verificaCompatibilidade(Class<?> classeTipoFonte, Class<?> classeTipoDestino) {
        /*VERIFICAÇÃO "NA MÃO":
        boolean compativel;

        if (classeTipoFonte.isInterface()) { //verificando se a classe fonte é uma interface, e se alguma de suas implementações é a classe de destino em questão
            compativel = Stream.of(classeTipoDestino.getInterfaces())
                    .anyMatch(interfaceImplementada -> interfaceImplementada.equals(classeTipoFonte));
        } else { //verificando se a classe de destino é uma extensão da classe fonte, ou se a classe destino é igual a classe fonte.
            compativel = classeTipoDestino.getSuperclass().equals(classeTipoFonte)
                    || classeTipoDestino.equals(classeTipoFonte);
        }
        return compativel;

        return classeTipoFonte.isAssignableFrom(classeTipoDestino);
    }*/

}
