package modelo;
import excecao.RegraNegocioException;
public class Estacionamento implements Tarifavel {
    private String placa;
    private double valorHora;
    private int horas;
    public Estacionamento(String placa, double valorHora, int horas) throws RegraNegocioException {
        setPlaca(placa);
        setValorHora(valorHora);
        setHoras(horas);
    }
    public void setPlaca(String placa) throws RegraNegocioException {
        if(placa == null || placa.isBlank()) throw new RegraNegocioException("Informe a placa do veículo");
        this.placa = placa.trim().toUpperCase();
    }
    public void setValorHora(double valorHora) throws RegraNegocioException {
        if(valorHora <= 0) throw new RegraNegocioException("Valor da hora deve ser maior que zero");
        this.valorHora = valorHora;
    }
    public void setHoras(int horas) throws RegraNegocioException {
        if(horas <= 0) throw new RegraNegocioException("Informe ao menos 1 hora de estacionamento");
        this.horas = horas;
    }
    public String getPlaca() { return placa; }
    public double getValorHora() { return valorHora; }
    public int getHoras() { return horas; }
    @Override public double calcularValor() { return valorHora * horas; }
    @Override public String getDescricao() { return "Estacionamento " + placa + " (" + horas + "h)"; }
    @Override public String toString() { return String.format("%s - R$ %.2f", getDescricao(), calcularValor()); }
}
