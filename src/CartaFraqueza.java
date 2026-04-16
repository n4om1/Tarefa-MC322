public class CartaFraqueza extends Carta {

    private int acumulos;

    public CartaFraqueza(String nome, int custo, int acumulos) {
        super(nome, "Aplica " + acumulos + " de Fraqueza ao inimigo, reduzindo seu ataque.", custo);
        this.acumulos = acumulos;
    }

    // Aplica Fraqueza no inimigo; o efeito reduz o dano do próximo ataque dele
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e aplica " + acumulos + " de Fraqueza em " + inimigo.getNome() + "!");
        inimigo.AplicarEfeito(new EfeitoFraqueza(inimigo, acumulos), combate);
    }
}
