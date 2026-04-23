/**
 * Classe abstrata que define a estrutura fundamental de uma carta no jogo.
 * Serve como base para todos os tipos de cartas (ataque, defesa, efeitos).
 * Cada subclasse deve implementar sua própria lógica de efeito no método {@code Usar}.
 */
public abstract class Carta {

    /** O nome identificador da carta. */
    private String nome;

    /** Uma breve descrição do efeito ou tipo da carta para exibição ao jogador. */
    private String descricao;

    /** O custo de energia necessário para utilizar a carta. */
    private int custo;

    /**
     * Construtor base para as cartas.
     *
     * @param nome O nome da carta.
     * @param descricao Texto explicativo do efeito.
     * @param custo Valor em energia a ser subtraído do herói.
     */
    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    /**
     * Método abstrato que define a ação executada quando a carta é jogada.
     * A implementação deve conter a lógica de interação entre herói, inimigo e o contexto do combate.
     *
     * @param heroi O herói que está utilizando a carta.
     * @param inimigo O alvo inimigo da ação.
     * @param combate A instância do combate atual, permitindo a inscrição de 
     * subscribers ou modificação do estado da batalha.
     */
    public abstract void Usar(Heroi heroi, Inimigo inimigo, Combate combate);

    /**
     * Obtém o nome da carta.
     * @return O nome da carta.
     */
    public String getNome() { return nome; }

    /**
     * Obtém a descrição do tipo ou efeito da carta.
     * @return A descrição da carta.
     */
    public String getDescricao() { return descricao; }

    /**
     * Obtém o custo de energia da carta.
     * @return O valor do custo.
     */
    public int getCusto() { return custo; }

    /**
     * Fornece uma representação textual formatada da carta, útil para interfaces de console.
     * Exemplo: "Espada [Custo: 1] - Ataque Físico"
     * @return Uma String contendo o nome, custo e descrição detalhada.
     */
    public String Descricao() {
        return nome + " [Custo: " + custo + "] - " + descricao;
    }
}
