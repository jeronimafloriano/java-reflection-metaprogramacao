package processador.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorDeConstrutor {

    private Constructor<?> construtor;
    ManipuladorDeConstrutor(Constructor<?> construtor){
        this.construtor = construtor;
    }

    public Object invoca(){
        try {
            return this.construtor.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha no construtor da classe.", e);
        }
    }
}
