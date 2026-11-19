import java.time.LocalDate;

public class Evento{
    private int codigo;
    private String nome;
    private LocalDate data;
    private int lotacao;
    private int ingressosVendidos;

    @Override 
    public String toString(){
        return "Codigo: " + codigo + "\nNome: " + nome + "\nData: " + data + 
        "\nLotacao: " + lotacao + "\nIngressos vendidos: " + ingressosVendidos + "\n";
    }
    
    public int getVagasRestantes(){
        int rest = lotacao - ingressosVendidos;
        return rest;
    }
    
    public void reservar(int qtd)
        throws LotacaoEsgotadaException{

        ingressosVendidos += qtd;

        if(vagasRestantes() == 0){
            throw new LotacaoEsgotadaException("Ingressos esgotados.\n");
        }        
    }

    public int getCodigo(){
        return codigo;
    }
    
    public String getNome(){
        return nome;
    }
}