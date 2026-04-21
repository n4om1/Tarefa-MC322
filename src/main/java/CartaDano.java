//package main.java;

public class CartaDano extends Carta {

    private int dano;

    public CartaDano(String nome, int custo, int dano) {
        super(nome, "Causa " + dano + " de dano ao inimigo.", custo);
        this.dano = dano;
    }

    // Causa dano a um inimigo quando utilizada
    @Override
    public void Usar(Heroi heroi, Inimigo inimigo, Combate combate) {
        System.out.println(heroi.getNome() + " usa " + getNome() + " e causa " + dano + " de dano em " + inimigo.getNome() + "!");
        inimigo.ReceberDano(dano);
        System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " de vida.");
    }

    public int getDano() { return dano; }
}
