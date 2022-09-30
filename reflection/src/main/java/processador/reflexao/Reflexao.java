package processador.reflexao;

public class Reflexao {


    public ManipuladorDeClasses refleteClasse(String fullQualifiedName) {
        try {
            Class<?> classe = Class.forName(fullQualifiedName);
            return new ManipuladorDeClasses(classe);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
