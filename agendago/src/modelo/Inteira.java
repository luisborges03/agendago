package modelo;
import excecao.RegraNegocioException;
public class Inteira extends Ingresso {
    public Inteira(int codigo, double valorBase) throws RegraNegocioException {
        super(codigo, valorBase);
    }
    @Override public double calcularValor() { return getValorBase(); }
}
//aqui nao mudou muito :)