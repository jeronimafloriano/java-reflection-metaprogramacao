package processador.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorDeClasses {

    private Class<?> classe;

    public ManipuladorDeClasses(Class<?> classe){
        this.classe = classe;
    }

    public ManipuladorDeConstrutor getConstrutorPadrao(){
        try {
            Constructor<?> construtor =  this.classe.getDeclaredConstructor();
            return new ManipuladorDeConstrutor(construtor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ManipuladorDeObjeto criaInstancia() {
        Object instancia = getConstrutorPadrao().invoca();
        return new ManipuladorDeObjeto(instancia);
    }
}
