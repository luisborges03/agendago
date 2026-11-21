import excecao.LotacaoEsgotadaException;
import excecao.RegraNegocioException;
import modelo.*;
import servico.Bilheteria;

import java.time.LocalDate;
import java.util.List;

/** Teste pelo console da Etapa 2: exercita o domínio e mostra as exceções sendo tratadas. */
public class TesteConsole {
    public static void main(String[] args) {
        Bilheteria b = new Bilheteria();

        titulo("1. Cadastros");
        try {
            b.cadastrarEvento("Show de Rock", LocalDate.of(2026, 12, 10), 5);
            b.cadastrarEvento("Palestra Java", LocalDate.of(2026, 11, 30), 50);
            b.cadastrarEvento("Feira de Tecnologia", LocalDate.of(2027, 1, 15), 200);
            b.cadastrarParticipante("12345678901", "Julia Nicole", "julia@email.com");
            b.cadastrarParticipante("98765432100", "Luis Fernando", "luis@email.com");
            b.listarEventos().forEach(System.out::println);
            b.listarParticipantes().forEach(System.out::println);
        } catch (RegraNegocioException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        titulo("2. Validações (cada linha deve mostrar um erro tratado)");
        tentar(() -> b.cadastrarParticipante("123", "Fulano", "f@x.com"));
        tentar(() -> b.cadastrarParticipante("11111111111", "", "f@x.com"));
        tentar(() -> b.cadastrarParticipante("12345678901", "Duplicado", "d@x.com"));
        tentar(() -> b.cadastrarEvento("Sem lotação", LocalDate.now(), 0));
        tentar(() -> b.alterarEvento(99, "Nada", LocalDate.now(), 10));

        titulo("3. Busca (sobrecarga e sem diferenciar maiúsculas)");
        System.out.println("buscar(2)               -> " + b.buscar(2));
        System.out.println("buscar(\"SHOW DE ROCK\")  -> " + b.buscar("SHOW DE ROCK"));
        System.out.println("buscar(\"inexistente\")   -> " + b.buscar("inexistente"));
        System.out.println("por nome 'ju'           -> " + b.buscarParticipantesPorNome("ju"));

        titulo("4. Polimorfismo: venda com ingressos e estacionamento");
        try {
            Evento rock = b.buscar("Show de Rock");
            Participante julia = b.buscarParticipante("12345678901");
            // lista do tipo da interface/abstrato: o serviço não sabe qual é o tipo concreto
            List<ItemVenda> itens = List.of(
                    new ItemVenda(new Inteira(1, 100), 1),
                    new ItemVenda(new MeiaEntrada(2, 100), 1),
                    new ItemVenda(new Vip(3, 100, 80), 1),
                    new ItemVenda(new Estacionamento("abc1d23", 10, 3), 1));
            for (ItemVenda i : itens)
                System.out.printf("  %-45s subtotal R$ %.2f%n", i.getItem(), i.calcularSubtotal());
            Venda v = b.vender(rock, julia, itens);
            System.out.println(v);
            System.out.println("Vagas restantes: " + rock.vagasRestantes() + " (estacionamento não ocupa vaga)");
        } catch (RegraNegocioException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        titulo("5. Lotação esgotada (exceção personalizada)");
        try {
            Evento rock = b.buscar(1);
            b.vender(rock, b.buscarParticipante("98765432100"),
                    List.of(new ItemVenda(new Inteira(10, 100), 3)));   // só restam 2
        } catch (LotacaoEsgotadaException e) {
            System.out.println("LotacaoEsgotadaException tratada: " + e.getMessage());
        } catch (RegraNegocioException e) {
            System.out.println("RegraNegocioException: " + e.getMessage());
        } finally {
            System.out.println("(finally) tentativa de venda encerrada.");
        }

        titulo("6. Regras de remoção");
        tentar(() -> b.removerEvento(1));            // tem vendas
        tentar(() -> b.removerParticipante("12345678901")); // tem vendas
        tentar(() -> { b.removerEvento(3); System.out.println("Evento 3 removido com sucesso."); });

        titulo("7. Relatórios ordenados");
        System.out.println("Eventos por data:");
        b.eventosPorData().forEach(e -> System.out.println("  " + e));
        System.out.println("Eventos por nome:");
        b.eventosPorNome().forEach(e -> System.out.println("  " + e));
        System.out.println("Vendas por data:");
        b.vendasPorData().forEach(v -> System.out.println("  " + v));
    }

    private interface Acao { void executar() throws RegraNegocioException; }

    private static void tentar(Acao a) {
        try { a.executar(); }
        catch (RegraNegocioException e) { System.out.println("  Erro tratado: " + e.getMessage()); }
    }
    private static void titulo(String t) { System.out.println("\n=== " + t + " ==="); }
}
