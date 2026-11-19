import java.util.ArrayList;
import java.util.List;

public class Bilheteria{
    private List<Participante> participantes;
    private List<Venda> vendas;
    private List<Evento> eventos;

    public Bilheteria(){
        this.participantes = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public Venda vender(Evento evento, Participante participante, List<ItemVenda> itens)
        throws LotacaoEsgotadaException{
        
            int qtdIngressos = 0;
            for(ItemVenda item : itens){
                if(item.getItem() instanceof Ingresso){
                    qtdIngressos += item.getQuantidade();
                }
            }

            if(qtdIngressos > evento.getVagasRestantes()){
                throw new LotacaoEsgotadaException("Lotacao esta esgotada para o evento") + 
                evento.getNome() + ".\n";
            }

            evento.reservar(qtdIngressos);

            Venda venda = new Venda(vendas.size() + 1, itens);
            vendas.add(venda);

            if(!participantes.contains(participante)){
                participantes.add(participante);
            }
            
            return venda;
    }

    public Evento buscar(int codigo){
        for(Evento e : eventos){
            if(e.getCodigo() == codigo){
                return e;
            }
        }

        return null;
    }

    public double calcularTotal(List<ItemVenda> itens){
        double total = 0;
        for(ItemVenda item : itens){
            total += item.calcularSubtotal();
        }

        return total;
    }
}