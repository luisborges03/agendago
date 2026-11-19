public class Vip extends Ingresso{
    private double adicional;

    public double calcularValor(){
        return valorBase + adicional;
    }
}