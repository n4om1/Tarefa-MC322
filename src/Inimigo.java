public class Inimigo extends Entidade {

    private int dano;

    public Inimigo(String nome, int vida, int dano) {
        super(nome, vida);
        this.dano = dano;
    }

    // Realiza um ataque contra o herói
    public void Atacar(Heroi heroi) {
        heroi.ReceberDano(dano);
    }

    // Desafio extra: anuncia a intenção de ataque no início do turno
    public String AnunciarIntencao() {
        return getNome() + " pretende causar " + dano + " de dano.";
    }

    public int getDano() { return dano; }
}