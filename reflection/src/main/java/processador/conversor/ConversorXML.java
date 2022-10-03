package processador.conversor;

import estoque.anotacao.NomeTagXml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {
    public String converte(Object objeto) {
        try {
            Class<?> classe = objeto.getClass();
            NomeTagXml anotacao = classe.getDeclaredAnnotation(NomeTagXml.class);

            StringBuilder xmlBuilder = new StringBuilder();

            if (objeto instanceof Collection) {
                Collection<?> colecao = (Collection<?>) objeto;

                xmlBuilder.append("<lista>");

                for (Object o : colecao) {
                    String xml = converte(o);
                    xmlBuilder.append(xml);
                }

                xmlBuilder.append("</lista>");
            } else {
                String nomeClasse = verificaAnotacao(anotacao)
                                    ? classe.getName()
                                    : anotacao.value();

                xmlBuilder.append("<" + nomeClasse + ">");

                for (Field atributo : classe.getDeclaredFields()) {
                    atributo.setAccessible(true);

                    String nomeAtributo = verificaAnotacao(anotacao)
                                            ? atributo.getName()
                                            : anotacao.value();

                    var valorAtributo = atributo.get(objeto);

                    xmlBuilder.append("<" + nomeAtributo + ">");
                    xmlBuilder.append(valorAtributo);
                    xmlBuilder.append("</" + nomeAtributo + ">");
                }
                xmlBuilder.append("</" + nomeClasse + ">");
            }

            return xmlBuilder.toString();

        } catch(IllegalAccessException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar o XML! " + e);
        }
    }

    private boolean verificaAnotacao(Annotation anotacao){
        return anotacao == null;
    }


}
