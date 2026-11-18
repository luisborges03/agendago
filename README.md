############################ AgendaGo - Sistema de Eventos ############################

== Equipe ==

    Integrantes:
        Julia Nicole
        Luis Fernando

== Dominios ==

    Hierarquia:
        Ingresso(classe abstrata) com subclasses Inteira, MeiaEntrada e Vip.
        Interface: Tarifavel, implementada por Ingresso e Estacionamento.
        Operacao principal: Vender ingressos respeitando a lotacao.

== Descricao do Problema ==
    
    Pequenos produtores de eventos (shows, palestras, feiras) costumam controlar a venda de ingressos em planilhas ou anotacoes manuais. Isso causa vendas acima da capacidade do local, erros no calculo de valores (meia-entrada, ingressos VIP, estacionamento) e perda de informacao entre um dia e outro. O AgendaGo e uma aplicacao Java de pequeno porte que organiza esse processo: a bilheteria cadastra eventos e participantes, registra vendas compostas por varios itens (ingressos de tipos diferentes e estacionamento), calcula o valor de cada item e o total da venda e bloqueia a venda quando a lotacao do evento e excedida, informando o erro ao operador. Os dados sao gravados em arquivo e recarregados ao abrir o sistema.

== Requisitos Funcionais ==

RF01:   
Cadastrar, listar, alterar e remover eventos (codigo, nome, data e lotacao), validando 
os dados informados.
Classe(s): Evento, Bilheteria.

RF02: 
Cadastrar, listar, alterar e remover participantes (CPF, nome e e-mail), validando os dados informados.
Classe(s):Participante, Bilheteria.

RF03: 
Buscar eventos por codigo ou por nome; a busca por nome nao diferencia maiusculas de minusculas.
Classe(s): Bilheteria (sobrecarga de buscar).

RF04: 
Registrar uma venda para um participante em um evento, composta por um ou mais itens (tipo de item e quantidade).
Classe(s): Venda, ItemVenda, Bilheteria.

RF05: 
Calcular o valor de cada item: Inteira = valor base; MeiaEntrada = valor base com desconto; Vip = valor base mais adicional; Estacionamento = valor da hora x horas.
Classe(s): Ingresso e subclasses, Estacionamento, Tarifavel.

RF06: 
Calcular o total da venda somando o subtotal (quantidade x valor) de cada item, sem verificar o tipo concreto do item.
Classe(s): ItemVenda, Bilheteria.

RF07: 
Controlar a lotacao: consultar as vagas restantes e impedir a venda quando a quantidade exceder a capacidade, lancando LotacaoEsgotadaException com mensagem exibida ao usuario.
Classe(s): Evento, LotacaoEsgotadaException.

RF08: 
Emitir relatorios ordenados: eventos por data ou nome e vendas por data.
Classe(s): Bilheteria.

RF09: 
Salvar eventos, participantes e vendas em arquivo CSV/texto e carrega-los ao iniciar, tratando arquivo ausente ou corrompido.
Classe(s): Camada de persistencia.

RF10: 
Oferecer interface grafica Swing com tela de cadastro (formulario) e tela de listagem (tabela), exibindo erros de regra de negocio em mensagem ao usuario.
Classe(s): Pacote gui.