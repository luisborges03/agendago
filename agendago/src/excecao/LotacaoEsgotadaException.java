package excecao;
public class LotacaoEsgotadaException extends RegraNegocioException {
    private static final long serialVersionUID = 1L;
    public LotacaoEsgotadaException(String mensagem) { super(mensagem); }
}
