/**
 * Interface que define o contrato para itens à venda na {@link EventoLoja}.
 * Cada implementação encapsula um comportamento de compra distinto — este é o
 * papel da interface no padrão de projeto <b>Strategy</b>: permitir que
 * diferentes algoritmos (tipos de item) sejam intercambiáveis sem alterar o
 * código da loja.
 *
 * <p>Padrão de projeto: <b>Strategy</b>
 * (fonte: <a href="https://refactoring.guru/design-patterns/strategy">refactoring.guru</a>)</p>
 */
public interface ItemLoja {
    /** Descrição completa do item exibida na loja (inclui preço). */
    String getDescricao();

    /** Preço em ouro do item. */
    int getPreco();

    /**
     * Aplica o efeito de compra sobre o estado do jogador.
     * Deve verificar internamente se o jogador possui ouro suficiente.
     * @param estado O estado atual do jogador.
     */
    void Aplicar(EstadoJogador estado);
}
