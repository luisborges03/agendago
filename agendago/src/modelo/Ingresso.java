package modelo;
import excecao.RegraNegocioException;
public abstract class Ingresso implements Tarifavel {
    private final int codigo;
    private final double valorBase;
    protected Ingresso(int codigo, double valorBase) throws RegraNegocioException {
        if(codigo <= 0) throw new RegraNegocioException("Código do ingresso deve ser positivo");
        if(valorBase <= 0) throw new RegraNegocioException("Valor base deve ser maior que zero");
        this.codigo = codigo;
        this.valorBase = valorBase;
    }
    //eu alterei para cada tipo de ingresso define seu preço
    @Override public abstract double calcularValor();
    @Override public int vagasOcupadas() { return 1; }
    @Override public String getDescricao() { return getClass().getSimpleName() + " #" + codigo; }
    public int getCodigo() { return codigo; }
    protected double getValorBase() { return valorBase; }
    @Override public String toString() {
        return String.format("%s - R$ %.2f", getDescricao(), calcularValor());
    }
}
