package modelo;
import excecao.RegraNegocioException;
public class MeiaEntrada extends Ingresso {
    public static final double DESCONTO = 0.5;   //50% e o valor base da meia 
    public MeiaEntrada(int codigo, double valorBase) throws RegraNegocioException {
        super(codigo, valorBase);
    }
    @Override public double calcularValor() { return getValorBase() * DESCONTO; }
}
