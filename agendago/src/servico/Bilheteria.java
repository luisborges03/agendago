package servico;
import excecao.RegraNegocioException;
import modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//:(
//Serviço central trabalha apenas com os tipos abstratos(Tarifavel/ItemVenda) nunca usa instanceof, getClass() ou cast para descobrir o tipo concreto.
public class Bilheteria {
    private final List<Evento> eventos = new ArrayList<>();
    private final List<Participante> participantes = new ArrayList<>();
    private final List<Venda> vendas = new ArrayList<>();
    private int proximoEvento = 1;
    private int proximaVenda = 1;
    //eventos
    public Evento cadastrarEvento(String nome, LocalDate data, int lotacao) throws RegraNegocioException {
        Evento e = new Evento(proximoEvento, nome, data, lotacao);
        eventos.add(e);
        proximoEvento++;
        return e;
    }
    public List<Evento> listarEventos() { return new ArrayList<>(eventos); }
    //sobrecarga de buscar
    public Evento buscar(int codigo) {
        for(Evento e : eventos) if (e.getCodigo() == codigo) return e;
        return null;
    }
    public Evento buscar(String nome) {
        for(Evento e : eventos) if (e.getNome().equalsIgnoreCase(nome.trim())) return e;
        return null;
    }
    public void alterarEvento(int codigo, String nome, LocalDate data, int lotacao) throws RegraNegocioException {
        Evento e = exigirEvento(codigo);
        e.setNome(nome);
        e.setData(data);
        e.setLotacao(lotacao);
    }
    public void removerEvento(int codigo) throws RegraNegocioException {
        Evento e = exigirEvento(codigo);
        for(Venda v : vendas)
            if(v.getEvento() == e) throw new RegraNegocioException("Evento possui vendas e não pode ser removido");
        eventos.remove(e);
    }
    //participantes
    public Participante cadastrarParticipante(String cpf, String nome, String email) throws RegraNegocioException {
        Participante novo = new Participante(cpf, nome, email);
        if(buscarParticipante(cpf) != null)
            throw new RegraNegocioException("Já existe participante com o CPF " + cpf + ".");
        participantes.add(novo);
        return novo;
    }
    public List<Participante> listarParticipantes() { return new ArrayList<>(participantes); }
    public Participante buscarParticipante(String cpf) {
        for(Participante p : participantes) if(p.getCpf().equals(cpf)) return p;
        return null;
    }
    public List<Participante> buscarParticipantesPorNome(String trecho) {
        List<Participante> r = new ArrayList<>();
        for(Participante p : participantes)
            if(p.getNome().toLowerCase().contains(trecho.trim().toLowerCase())) r.add(p);
        return r;
    }
    public void alterarParticipante(String cpf, String nome, String email) throws RegraNegocioException {
        Participante p = exigirParticipante(cpf);
        p.setNome(nome);
        p.setEmail(email);
    }
    public void removerParticipante(String cpf) throws RegraNegocioException {
        Participante p = exigirParticipante(cpf);
        for(Venda v : vendas)
            if(v.getParticipante() == p)
                throw new RegraNegocioException("Participante possui vendas e não pode ser removido.");
        participantes.remove(p);
    }
    //vendas
    //polimorfismo: a quantidade de vagas vem de cada item, sem checar o tipo
    public Venda vender(Evento evento, Participante participante, List<ItemVenda> itens)
            throws RegraNegocioException {
        Venda venda = new Venda(proximaVenda, evento, participante, itens); //valida os dados
        evento.reservar(venda.vagasOcupadas());                             //pode lançar LotacaoEsgotadaException
        vendas.add(venda);
        proximaVenda++;
        return venda;
    }
    public double calcularTotal(List<ItemVenda> itens) {
        double total = 0;
        for(ItemVenda i : itens) total += i.calcularSubtotal();
        return total;
    }
    public int proximoCodigoVenda() { return proximaVenda; }
    public List<Venda> listarVendas() { return new ArrayList<>(vendas); }
    //relatorios
    public List<Evento> eventosPorData() {
        List<Evento> r = listarEventos();
        r.sort(Comparator.comparing(Evento::getData).thenComparing(Evento::getNome));
        return r;
    }
    public List<Evento> eventosPorNome() {
        List<Evento> r = listarEventos();
        r.sort(Comparator.comparing(Evento::getNome, String.CASE_INSENSITIVE_ORDER));
        return r;
    }
    public List<Venda> vendasPorData() {
        List<Venda> r = listarVendas();
        r.sort(Comparator.comparing(Venda::getDataVenda).thenComparing(Venda::getCodigo));
        return r;
    }
    //auxiliares
    private Evento exigirEvento(int codigo) throws RegraNegocioException {
        Evento e = buscar(codigo);
        if(e == null) throw new RegraNegocioException("Evento " + codigo + " não encontrado");
        return e;
    }
    private Participante exigirParticipante(String cpf) throws RegraNegocioException {
        Participante p = buscarParticipante(cpf);
        if(p == null) throw new RegraNegocioException("Participante com CPF " + cpf + " não encontrado");
        return p;
    }
}
