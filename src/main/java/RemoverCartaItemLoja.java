import java.util.ArrayList;

/**
 * Estratégia concreta de {@link ItemLoja}: remove uma carta do baralho do jogador.
 * Útil para eliminar cartas fracas e enxugar o deck.
 * Implementa o padrão <b>Strategy</b>.
 */
public class RemoverCartaItemLoja implements ItemLoja {

    private static final int PRECO = 50;

    @Override
    public String getDescricao() {
        return "Remover uma carta do baralho — " + PRECO + " ouro";
    }

    @Override
    public int getPreco() { return PRECO; }

    @Override
    public void Aplicar(EstadoJogador estado) {
        if (!estado.GastarOuro(PRECO)) {
            System.out.println("Ouro insuficiente! (Você tem " + estado.getOuro() + " ouro)");
            return;
        }

        ArrayList<Carta> todas = estado.getBaralho().getTodas();
        if (todas.isEmpty()) {
            System.out.println("Seu baralho está vazio!");
            estado.AdicionarOuro(PRECO); // devolve o ouro
            return;
        }

        System.out.println("Qual carta deseja remover?");
        for (int i = 0; i < todas.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + todas.get(i).Descricao());
        }
        System.out.print("Escolha: ");

        int escolha = estado.getEntrada().nextInt();
        if (escolha >= 1 && escolha <= todas.size()) {
            Carta removida = todas.get(escolha - 1);
            estado.getBaralho().RemoverCarta(removida);
            System.out.println(removida.getNome() + " foi removida do baralho.");
        } else {
            System.out.println("Opção inválida. Ouro devolvido.");
            estado.AdicionarOuro(PRECO);
        }
    }
}
