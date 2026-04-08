public class CartaEscudo extends Carta {

    private int protecao;

    public CartaEscudo(String nome, int custo, int protecao) {
        super(nome, "Concede " + protecao + " de escudo ao herói.", custo);
        this.protecao = protecao;
    }

    // Dá escudo ao herói quando utilizada
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e ganha " + protecao + " de escudo!");
        heroi.GanharEscudo(protecao);
        System.out.println(heroi.getNome() + ": escudo atual = " + heroi.getEscudo() + ".");
    }

    public int getProtecao() { return protecao; }
}