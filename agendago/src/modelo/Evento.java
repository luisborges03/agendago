package modelo;
import excecao.LotacaoEsgotadaException;
import excecao.RegraNegocioException;
import java.time.LocalDate;
public class Evento {
    private final int codigo;
    private String nome;
    private LocalDate data;
    private int lotacao;
    private int ingressosVendidos;
    public Evento(int codigo, String nome, LocalDate data, int lotacao) throws RegraNegocioException {
        if (codigo <= 0) throw new RegraNegocioException("Código do evento deve ser positivo");
        this.codigo = codigo;
        setNome(nome);
        setData(data);
        setLotacao(lotacao);
    }
    public int vagasRestantes() { return lotacao - ingressosVendidos; }
    public void reservar(int qtd) throws LotacaoEsgotadaException {
        if (qtd > vagasRestantes())
            throw new LotacaoEsgotadaException("Lotação esgotada em \"" + nome + "\": restam "
                    + vagasRestantes() + " vaga(s) e foram pedidas " + qtd + ".");
        ingressosVendidos += qtd;
    }
    public void setNome(String nome) throws RegraNegocioException {
        if(nome == null || nome.isBlank()) throw new RegraNegocioException("Nome do evento é obrigatório");
        this.nome = nome.trim();
    }
    public void setData(LocalDate data) throws RegraNegocioException {
        if(data == null) throw new RegraNegocioException("Data do evento é obrigatória");
        this.data = data;
    }
    public void setLotacao(int lotacao) throws RegraNegocioException {
        if(lotacao <= 0) throw new RegraNegocioException("A lotação deve ser maior que zero");
        if(lotacao < ingressosVendidos)
            throw new RegraNegocioException("Lotação menor que os " + ingressosVendidos + " ingressos já vendidos");
        this.lotacao = lotacao;
    }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public LocalDate getData() { return data; }
    public int getLotacao() { return lotacao; }
    public int getIngressosVendidos() { return ingressosVendidos; }
    //usado ao carregar arquivos
    public void restaurarVendidos(int vendidos) throws RegraNegocioException {
        if (vendidos < 0 || vendidos > lotacao) throw new RegraNegocioException("Quantidade de vendidos inválida");
        this.ingressosVendidos = vendidos;
    }
    @Override public String toString() {
        return String.format("[%d] %s - %s - lotação %d, vendidos %d, vagas %d",
                codigo, nome, data, lotacao, ingressosVendidos, vagasRestantes());
    }
}
