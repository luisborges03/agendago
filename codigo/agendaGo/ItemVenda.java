public class ItemVenda extends Item{
    private int quantidade;

    public double calcularSubtotal(){
        double subtotal;

        subtotal = quantidade * getValor();
        return subtotal;
    }

    public int getQuantidade(){
        return quantidade;
    }
}