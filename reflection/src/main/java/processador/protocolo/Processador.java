package processador.protocolo;

import processador.reflexao.Reflexao;

import java.util.Map;

public class Processador {
    private String pacoteBase;

    public Processador(String pacoteBase) {
        this.pacoteBase = pacoteBase;
    }

    public Object executa(String url) {
        // /produto/lista

        Request request = new Request(url);

        String nomeController = request.getNomeController();
        String nomeMetodo = request.getNomeMetodo();
        Map<String, Object> params = request.getQueryParams();


        Object retorno = new Reflexao()
                .refleteClasse(pacoteBase + nomeController)
                .criaInstancia()
                .getMetodo(nomeMetodo, params)
                .comTratamentoDeExcecao((metodo, e) -> {
                    System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
                    throw new RuntimeException("ERRO!");
                })
                .invoca();


        return retorno;
    }



}
