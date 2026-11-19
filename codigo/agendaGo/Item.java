public class Item{
    private string nome;
    private double valor;

    public String toString(){
        return "Nome : " + nome + "\nValor: " + valor + "\n";
    }

    public String getItem(){
        return nome;
    }

    public double getValor(){
        return valor;
    }
}