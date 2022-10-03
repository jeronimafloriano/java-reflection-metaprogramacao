package processador.protocolo;

import estoque.dao.ProdutoDao;
import estoque.dao.ProdutoDaoMock;
import processador.conversor.ConversorXML;
import processador.ioc.ContainerIoC;
import processador.reflexao.ManipuladorDeObjeto;
import processador.reflexao.Reflexao;

import java.util.Map;

public class Processador {
    private String pacoteBase;
    private ContainerIoC container;

    public Processador(String pacoteBase) {
        this.pacoteBase = pacoteBase;
        this.container = new ContainerIoC();
    }

    public Object executa(String url) {
        // /produto/lista

        Request request = new Request(url);

        String nomeController = request.getNomeController();
        String nomeMetodo = request.getNomeMetodo();
        Map<String, Object> params = request.getQueryParams();


        Class<?> classeConstrole = new Reflexao().getClasse(pacoteBase + nomeController);
        Object instanciaControle = container.getInstancia(classeConstrole);

        Object retorno = new ManipuladorDeObjeto(instanciaControle)
                .getMetodo(nomeMetodo, params)
                .comTratamentoDeExcecao((metodo, e) -> {
                    System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
                    throw new RuntimeException("ERRO!");
                })
                .invoca();

        retorno = new ConversorXML().converte(retorno);

        return retorno;
    }


    public void registra(Class<ProdutoDao> classeTipoFonte, Class<ProdutoDaoMock> classeTipoDestino) {
        container.registra(classeTipoFonte, classeTipoDestino);

    }
}
