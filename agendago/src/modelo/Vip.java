package modelo;

import excecao.RegraNegocioException;
public class Vip extends Ingresso {
    private double adicional;
    public Vip(int codigo, double valorBase, double adicional) throws RegraNegocioException {
        super(codigo, valorBase);
        setAdicional(adicional);
    }
    public void setAdicional(double adicional) throws RegraNegocioException {
        if (adicional < 0) throw new RegraNegocioException("Adicional VIP não pode ser negativo");
        this.adicional = adicional;
    }
    public double getAdicional() { return adicional; }
    //como eu adicionei os exceptions mudou bastante a base de alguns codigos
    //tem muito mais verificacao mas isso e mais culpa da interface mesmo por isso deve ta meio confuso na sua cabeca
    @Override public double calcularValor() { return getValorBase() + adicional; }
}
