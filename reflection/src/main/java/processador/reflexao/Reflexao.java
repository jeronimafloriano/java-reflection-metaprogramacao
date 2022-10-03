package processador.reflexao;

public class Reflexao {


    public ManipuladorDeClasses refleteClasse(String fullQualifiedName) {
        Class<?> classe = getClasse(fullQualifiedName);
        return new ManipuladorDeClasses(classe);
    }

    public Class<?> getClasse(String fullQualifiedName){
        try {
            Class<?> classe = Class.forName(fullQualifiedName);
            return classe;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
