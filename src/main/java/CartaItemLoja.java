/**
 * Estratégia concreta de {@link ItemLoja}: adiciona uma carta ao baralho do jogador.
 * Implementa o padrão <b>Strategy</b> — o comportamento de compra é encapsulado
 * nesta classe, separado da lógica da {@link EventoLoja}.
 */
public class CartaItemLoja implements ItemLoja {

    private Carta carta;
    private int preco;

    /**
     * @param carta A carta que será adicionada ao baralho ao ser comprada.
     * @param preco O custo em ouro.
     */
    public CartaItemLoja(Carta carta, int preco) {
        this.carta = carta;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {
        return carta.Descricao() + " — " + preco + " ouro";
    }

    @Override
    public int getPreco() { return preco; }

    @Override
    public void Aplicar(EstadoJogador estado) {
        if (estado.GastarOuro(preco)) {
            estado.getBaralho().AdicionarCarta(carta);
            System.out.println("Comprou: " + carta.getNome() + "! Adicionada ao baralho.");
        } else {
            System.out.println("Ouro insuficiente! (Você tem " + estado.getOuro() + " ouro)");
        }
    }
}
