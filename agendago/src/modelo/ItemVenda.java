package modelo;
import excecao.RegraNegocioException;
public class ItemVenda {
    private final Tarifavel item;
    private final int quantidade;
    public ItemVenda(Tarifavel item, int quantidade) throws RegraNegocioException {
        if (item == null) throw new RegraNegocioException("Item da venda é obrigatório");
        if (quantidade <= 0) throw new RegraNegocioException("Quantidade deve ser maior que zero");
        this.item = item;
        this.quantidade = quantidade;
    }
    public double calcularSubtotal() { return item.calcularValor() * quantidade; }
    public int vagasOcupadas() { return item.vagasOcupadas() * quantidade; }
    public Tarifavel getItem() { return item; }
    public int getQuantidade() { return quantidade; }
    @Override public String toString() { return quantidade + "x " + item.getDescricao(); }
}
