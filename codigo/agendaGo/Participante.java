public class Participante{
    private String cpf;
    private String nome;
    private String email;

    @Override 
    public String toString(){
        return "Nome: " + nome + "\nCPF: " + cpf + "\nE-mail: " + email + "\n";
    }
}