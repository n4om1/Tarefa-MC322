/**
 * Implementação de {@link Carta} que aplica um efeito de dano progressivo.
 * Diferente da carta de dano direto, esta carta debilita o inimigo ao longo do tempo,
 * aplicando acúmulos que resultam em dano periódico.
 */
public class CartaVeneno extends Carta {

    /** A quantidade de acúmulos de veneno a serem adicionados ao alvo. */
    private int acumulos;

    /**
     * Construtor da carta de veneno.
     * Define o nome, custo e gera uma descrição automática indicando a 
     * quantidade de veneno que será aplicada.
     *
     * @param nome O nome da carta (ex: "Adaga Envenenada", "Nuvem Tóxica").
     * @param custo O custo de energia para utilizar a carta.
     * @param acumulos A quantidade de veneno (intensidade) a ser aplicada ao inimigo.
     */
    public CartaVeneno(String nome, int custo, int acumulos) {
        super(nome, "Aplica " + acumulos + " de Veneno ao inimigo.", custo);
        this.acumulos = acumulos;
    }

    /**
     * Aplica o efeito {@link EfeitoVeneno} ao inimigo alvo.
     * O efeito é registrado no sistema de combate e causará dano ao inimigo 
     * de forma persistente (geralmente ao final de cada turno).
     * * @param heroi O herói que utiliza a carta.
     * @param inimigo O alvo que será infectado pelo veneno.
     * @param combate O contexto do combate que processará o dano do veneno nos turnos seguintes.
     */
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e aplica " + acumulos + " de Veneno em " + inimigo.getNome() + "!");
        inimigo.AplicarEfeito(new EfeitoVeneno(inimigo, acumulos), combate);
    }
}
