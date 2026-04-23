/**
 * Implementação de {@link Carta} que aplica um debuff de redução de poder.
 * Esta carta não causa dano direto, mas altera o estado do inimigo ao 
 * aplicar o efeito de Fraqueza, impactando sua capacidade ofensiva futura.
 */
public class CartaFraqueza extends Carta {

    /** A quantidade de turnos ou intensidade do efeito de fraqueza a ser aplicado. */
    private int acumulos;

    /**
     * Construtor da carta de fraqueza.
     * Define o nome, custo e gera uma descrição automática explicando que 
     * o ataque do inimigo será reduzido.
     *
     * @param nome O nome da carta (ex: "Olhar Intimidador", "Grito de Guerra").
     * @param custo O custo de energia para utilizar a carta.
     * @param acumulos A intensidade do efeito (geralmente duração em turnos).
     */
    public CartaFraqueza(String nome, int custo, int acumulos) {
        super(nome, "Aplica " + acumulos + " de Fraqueza ao inimigo, reduzindo seu ataque.", custo);
        this.acumulos = acumulos;
    }

    /**
     * Aplica o efeito de debuff {@link EfeitoFraqueza} ao inimigo.
     * O efeito é registrado na lista de efeitos do alvo e gerenciado pelo 
     * contexto do combate para reduzir o dano do próximo ataque do inimigo.
     * @param heroi O herói que utiliza a carta.
     * @param inimigo O alvo que receberá o efeito de fraqueza.
     * @param combate O contexto do combate onde o efeito será processado e monitorado.
     */
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e aplica " + acumulos + " de Fraqueza em " + inimigo.getNome() + "!");
        inimigo.AplicarEfeito(new EfeitoFraqueza(inimigo, acumulos), combate);
    }
}
