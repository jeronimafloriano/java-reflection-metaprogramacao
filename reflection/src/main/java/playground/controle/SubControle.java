package playground.controle;

public class SubControle extends Controle{

    private SubControle(){}

    private SubControle(String s){}

    public SubControle(Integer i){}

    public void metodoSubControle1(){
        System.out.println("Executando método um da classe SubControle.");
    }

    private String metodoSubControle2(){
        System.out.println("Executando método um da classe SubControle.");
        return "Retorno do método 2";
    }

}
