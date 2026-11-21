package modelo;
import excecao.RegraNegocioException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//os itens venda so existe como parte de item venda(a venda guarda a propria lista)
public class Venda {
    private final int codigo;
    private final LocalDate dataVenda;
    private final Evento evento;
    private final Participante participante;
    private final List<ItemVenda> itens;
    public Venda(int codigo, Evento evento, Participante participante, List<ItemVenda> itens)
            throws RegraNegocioException {
        this(codigo, LocalDate.now(), evento, participante, itens);
    }
    //permite informar a data (usada ao carregar de arquivo)
    public Venda(int codigo, LocalDate dataVenda, Evento evento, Participante participante,
                 List<ItemVenda> itens) throws RegraNegocioException {
        if(codigo <= 0) throw new RegraNegocioException("Código da venda deve ser positivo");
        if(dataVenda == null) throw new RegraNegocioException("Data da venda é obrigatória");
        if(evento == null || participante == null)
            throw new RegraNegocioException("Evento e participante são obrigatórios");
        if(itens == null || itens.isEmpty())
            throw new RegraNegocioException("A venda precisa de ao menos um item");
        this.codigo = codigo;
        this.dataVenda = dataVenda;
        this.evento = evento;
        this.participante = participante;
        this.itens = new ArrayList<>(itens);
    }
    public double getTotal() {
        double total = 0;
        for (ItemVenda i : itens) total += i.calcularSubtotal();
        return total;
    }
    public int vagasOcupadas() {
        int n = 0;
        for (ItemVenda i : itens) n += i.vagasOcupadas();
        return n;
    }
    public int getCodigo() { return codigo; }
    public LocalDate getDataVenda() { return dataVenda; }
    public Evento getEvento() { return evento; }
    public Participante getParticipante() { return participante; }
    public List<ItemVenda> getItens() { return Collections.unmodifiableList(itens); }
    //esse override nao sei se ta certo
    @Override public String toString() {
        return String.format("Venda #%d (%s) - %s / %s - %s - total R$ %.2f",
                codigo, dataVenda, evento.getNome(), participante.getNome(), itens, getTotal());
    }
}
