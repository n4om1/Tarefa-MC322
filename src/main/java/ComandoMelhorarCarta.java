import java.util.ArrayList;

/**
 * Comando concreto da {@link EventoFogueira}: melhora uma carta do baralho do jogador.
 * Aumenta os atributos da carta escolhida (dano, escudo ou acúmulos de efeito).
 * Implementa o padrão <b>Command</b>.
 */
public class ComandoMelhorarCarta implements ComandoFogueira {

    @Override
    public String getDescricao() {
        return "Forjar — Melhorar uma carta do baralho";
    }

    @Override
    public void Executar(EstadoJogador estado) {
        ArrayList<Carta> todas = estado.getBaralho().getTodas();
        if (todas.isEmpty()) {
            System.out.println("Seu baralho está vazio!");
            return;
        }

        System.out.println("Escolha uma carta para melhorar:");
        for (int i = 0; i < todas.size(); i++) {
            String tag = todas.get(i).isMelhorada() ? " [JÁ MELHORADA]" : "";
            System.out.println("  " + (i + 1) + " - " + todas.get(i).Descricao() + tag);
        }
        System.out.print("Escolha: ");

        int escolha = estado.getEntrada().nextInt();
        if (escolha >= 1 && escolha <= todas.size()) {
            todas.get(escolha - 1).Melhorar();
        } else {
            System.out.println("Opção inválida. Nenhuma carta foi melhorada.");
        }
    }
}
