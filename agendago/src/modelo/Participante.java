package modelo;
import excecao.RegraNegocioException;
public class Participante {
    private final String cpf;
    private String nome;
    private String email;
//erro se o cpf for digitado errado
    public Participante(String cpf, String nome, String email) throws RegraNegocioException {
        if(cpf == null || !cpf.matches("\\d{11}"))
            throw new RegraNegocioException("CPF deve ter 11 dígitos numéricos");
        this.cpf = cpf;
        setNome(nome);
        setEmail(email);
    }
    public void setNome(String nome) throws RegraNegocioException {
        if (nome == null || nome.isBlank()) throw new RegraNegocioException("Nome do participante é obrigatório");
        this.nome = nome.trim();
    }
    public void setEmail(String email) throws RegraNegocioException {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
            throw new RegraNegocioException("E-mail inválido");
        this.email = email.trim();
    }
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    @Override public String toString() { return nome + " (CPF " + cpf + ", " + email + ")"; }
}
