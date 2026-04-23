/**
 * Implementação de {@link Carta} focada em defesa.
 * Esta classe representa cartas que mitigam danos futuros ao adicionar 
 * pontos de escudo ao herói.
 */
public class CartaEscudo extends Carta {

    /** A quantidade de pontos de proteção que serão adicionados ao escudo do herói. */
    private int protecao;

    /**
     * Construtor da carta de escudo.
     * Define o nome, custo e gera uma descrição automática baseada no valor de proteção.
     *
     * @param nome O nome da carta (ex: "Escudo de Madeira", "Barreira Mística").
     * @param custo O custo de energia para utilizar a carta.
     * @param protecao A quantidade de escudo a ser concedida ao herói.
     */
    public CartaEscudo(String nome, int custo, int protecao) {
        super(nome, "Concede " + protecao + " de escudo ao herói.", custo);
        this.protecao = protecao;
    }

    /**
     * Aplica o efeito de defesa ao herói.
     * Incrementa o valor de escudo do herói, permitindo que ele absorva dano 
     * no próximo ataque inimigo sem reduzir sua vida diretamente.
     * @param heroi O herói que receberá a proteção.
     * @param inimigo O adversário (não é afetado diretamente por esta carta).
     * @param combate O contexto do combate atual.
     */
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e ganha " + protecao + " de escudo!");
        heroi.GanharEscudo(protecao);
        System.out.println(heroi.getNome() + ": escudo atual = " + heroi.getEscudo() + ".");
    }

    /**
     * Obtém o valor de proteção concedido por esta carta.
     * @return O valor do escudo.
     */
    public int getProtecao() { return protecao; }
}
