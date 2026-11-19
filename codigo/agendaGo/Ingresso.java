public abstract class Ingresso implements Tarifavel{
    private int codigo;
    protected double valorBase;

    public abstract double calcularValor();
    
    public String getDescricao(){
        return "Descricao" + "\n";
    }

    @Override 
    public String toString(){
        return "Codigo: " + codigo + "\nValor base: " + valorBase + "\n";
    }
}