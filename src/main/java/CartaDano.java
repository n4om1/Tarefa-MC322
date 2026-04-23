/**
 * Implementação específica de uma {@link Carta} focada em causar dano direto.
 * Esta classe representa cartas de ataque ofensivo que reduzem os pontos de vida do inimigo.
 */
public class CartaDano extends Carta {

    /** A quantidade base de pontos de dano que esta carta inflige. */
    private int dano;

    /**
     * Construtor da carta de dano.
     * Define o nome, custo e calcula automaticamente a descrição com base no dano fornecido.
     *
     * @param nome O nome da carta (ex: "Espada", "Flechada").
     * @param custo O custo de energia para usar a carta.
     * @param dano A quantidade de dano que será causada ao alvo.
     */
    public CartaDano(String nome, int custo, int dano) {
        super(nome, "Causa " + dano + " de dano ao inimigo.", custo);
        this.dano = dano;
    }

    /**
     * Executa a ação de ataque da carta.
     * O herói consome a carta para reduzir a vida do inimigo e exibe o status 
     * atualizado do alvo no console.
     * @param heroi O herói que está realizando o ataque.
     * @param inimigo O inimigo que sofrerá o dano.
     * @param combate O contexto do combate atual.
     */
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e causa " + dano + " de dano em " + inimigo.getNome() + "!");
        inimigo.ReceberDano(dano);
        System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " de vida.");
    }
    
    /**
     * Obtém o valor de dano fixo desta carta.
     * @return O valor do dano.
     */
    public int getDano() { return dano; }
}
