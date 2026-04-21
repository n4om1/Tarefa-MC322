//package main.java;
public class Inimigo extends Entidade {

    private int dano;

    // Lista de ações possíveis e índice da ação atual (rotativo)
    private String[] acoes;
    private int acaoAtual;

    public Inimigo(String nome, int vida, int dano) {
        super(nome, vida);
        this.dano = dano;
        this.acoes = new String[]{"atacar", "aplicarForca"};
        this.acaoAtual = 0;
    }

    // Seleciona e executa a ação atual de forma rotativa
    public void ExecutarAcao(Heroi heroi, Combate combate) {
        switch (acoes[acaoAtual]) {
            case "atacar":
                Atacar(heroi, combate);
                break;
            case "aplicarForca":
                AplicarForcaEmSi(combate);
                break;
        }
        acaoAtual = (acaoAtual + 1) % acoes.length;
    }

    // Realiza um ataque contra o herói, considerando bônus e penalidades de efeitos
    public void Atacar(Heroi heroi, Combate combate) {
        combate.NotificarAtaque(this);
        int danoTotal = Math.max(0, dano + combate.getDanoBonus());
        int escudoAntes = heroi.getEscudo();
        heroi.ReceberDano(danoTotal);

        if (escudoAntes > 0) {
            System.out.println(getNome() + " ataca com " + danoTotal + " de dano. O escudo absorve parte do golpe! "
                    + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
        } else {
            System.out.println(getNome() + " ataca com " + danoTotal + " de dano! "
                    + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
        }
    }

    // Aplica Força em si mesmo como ação alternativa ao ataque
    private void AplicarForcaEmSi(Combate combate) {
        System.out.println(getNome() + " aplica Força em si mesmo!");
        AplicarEfeito(new EfeitoForca(this, 2), combate);
    }

    // Anuncia a próxima intenção com base na ação atual
    public String AnunciarIntencao() {
        switch (acoes[acaoAtual]) {
            case "atacar":
                return getNome() + " pretende causar " + dano + " de dano.";
            case "aplicarForca":
                return getNome() + " pretende aplicar Força em si mesmo.";
            default:
                return getNome() + " está planejando algo...";
        }
    }

    public int getDano() { return dano; }
}
