package modelo;
//valor e descricao, implementei por hierarquias diferentes
public interface Tarifavel {
    String getDescricao();
    double calcularValor();
    //por padrao teoricamente o vento ocupa nenhuma vaga, ex estacionamento sei la ta confuso
    default int vagasOcupadas() { return 0; }
}
