import java.util.function.Consumer;

/**
 * Representa uma opção dentro de um evento {@link Escolha}.
 * Encapsula uma descrição textual e o efeito aplicado ao jogador ao escolhê-la.
 */
public class OpcaoEscolha {

    private String descricao;
    private Consumer<EstadoJogador> efeito;

    /**
     * @param descricao Texto exibido ao jogador descrevendo a opção.
     * @param efeito    Lambda que aplica as consequências sobre o {@link EstadoJogador}.
     */
    public OpcaoEscolha(String descricao, Consumer<EstadoJogador> efeito) {
        this.descricao = descricao;
        this.efeito = efeito;
    }

    /** Executa o efeito desta opção sobre o estado do jogador. */
    public void Executar(EstadoJogador estado) {
        efeito.accept(estado);
    }

    public String getDescricao() { return descricao; }
}
