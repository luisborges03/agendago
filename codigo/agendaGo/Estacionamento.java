public class Estacionamento implements Tarifavel{
    private String placa;
    private double valorHora;
    private int horas;

    public double calcularValor(){
        return valorHora * horas;
    }
    public String getDescricao(){
        return "Descricao" + "\n";
    }

    @Override 
    public String toString(){
        return "Placa: " + placa + "\nValor hora: " + valorHora +
        "\nHoras: " + horas + "\n";
    }
}