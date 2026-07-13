/**
 * Classe abstrata que representa qualquer evento presente em um nó do mapa.
 * Pode ser uma batalha, uma loja, uma fogueira, uma escolha narrativa, ou qualquer
 * outro sistema que modifique o estado do jogador.
 */
public abstract class Evento {

    private String nome;

    /**
     * @param nome Nome do evento exibido ao jogador na escolha de caminho.
     */
    public Evento(String nome) {
        this.nome = nome;
    }

    /**
     * Executa o evento, podendo modificar o {@link EstadoJogador} de diversas formas.
     * O sistema de mapa verifica se o herói continua vivo após cada chamada.
     * @param estado O estado atual do jogador (herói, baralho, ouro, input).
     */
    public abstract void Iniciar(EstadoJogador estado);

    public String getNome() { return nome; }
}
