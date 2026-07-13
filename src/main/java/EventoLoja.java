import java.util.ArrayList;
import java.util.Random;

/**
 * Evento de loja no mapa. O jogador pode gastar ouro para comprar cartas ou
 * remover cartas indesejadas do baralho.
 *
 * <p>Utiliza o padrão <b>Strategy</b> ({@link ItemLoja}): cada item na vitrine
 * encapsula seu próprio comportamento de compra, permitindo adicionar novos
 * tipos de item sem alterar o código desta classe.</p>
 */
public class EventoLoja extends Evento {

    private static final int NUM_CARTAS = 3;
    private final Random rand = new Random();

    public EventoLoja() {
        super("Loja");
    }

    @Override
    public void Iniciar(EstadoJogador estado) {
        System.out.println("=== LOJA ===");
        System.out.println("Ouro disponível: " + estado.getOuro());
        System.out.println();

        // Gera itens à venda — cada carta é uma estratégia concreta de CartaItemLoja
        ArrayList<ItemLoja> itens = new ArrayList<>();
        for (int i = 0; i < NUM_CARTAS; i++) {
            int preco = 30 + rand.nextInt(31); // 30–60 ouro
            itens.add(new CartaItemLoja(PoolCartas.CartaAleatoria(), preco));
        }
        itens.add(new RemoverCartaItemLoja());

        System.out.println("Itens disponíveis:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + itens.get(i).getDescricao());
        }
        System.out.println("  0 - Sair da loja");
        System.out.println();

        int escolha = -1;
        while (escolha != 0) {
            System.out.print("Comprar: ");
            escolha = estado.getEntrada().nextInt();
            if (escolha == 0) break;
            if (escolha >= 1 && escolha <= itens.size()) {
                itens.get(escolha - 1).Aplicar(estado);
                System.out.println("Ouro restante: " + estado.getOuro());
            } else {
                System.out.println("Opção inválida.");
            }
            System.out.println();
        }

        System.out.println("Até a próxima!");
        System.out.println();
    }
}
