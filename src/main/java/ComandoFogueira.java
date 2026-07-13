/**
 * Interface que define o contrato para ações disponíveis na {@link EventoFogueira}.
 * Cada implementação encapsula uma ação distinta (descansar, melhorar carta) —
 * este é o papel do padrão de projeto <b>Command</b>: transformar requisições em
 * objetos independentes, facilitando a extensão sem alterar o código da fogueira.
 *
 * <p>Padrão de projeto: <b>Command</b>
 * (fonte: <a href="https://refactoring.guru/design-patterns/command">refactoring.guru</a>)</p>
 */
public interface ComandoFogueira {
    /** Descrição da ação exibida ao jogador. */
    String getDescricao();

    /**
     * Executa a ação sobre o estado do jogador.
     * @param estado O estado atual do jogador.
     */
    void Executar(EstadoJogador estado);
}
