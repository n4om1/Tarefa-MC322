public class CartaVeneno extends Carta {

    private int acumulos;

    public CartaVeneno(String nome, int custo, int acumulos) {
        super(nome, "Aplica " + acumulos + " de Veneno ao inimigo.", custo);
        this.acumulos = acumulos;
    }

    // Aplica acúmulos de Veneno no inimigo; o efeito age ao final de cada turno do jogador
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e aplica " + acumulos + " de Veneno em " + inimigo.getNome() + "!");
        inimigo.AplicarEfeito(new EfeitoVeneno(inimigo, acumulos), combate);
    }
}
