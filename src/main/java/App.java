import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal. Configura o estado inicial do jogador, monta o mapa e
 * executa o loop de progressão entre eventos.
 *
 * <p>Mapa gerado:
 * <pre>
 *  [Rato Gigante] --> [Altar Misterioso] --> [Goblin Feroz] --> [Dragão] (final)
 *                --> [Loja]             --> [Fogueira]     --> [Lich]    (final)
 * </pre>
 * </p>
 */
public class App {

    private App() {}

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Estado persistente do jogador (vida + baralho + ouro)
        Heroi heroi = new Heroi("Herói", 40, 3);
        Baralho baralho = MontarBaralho();
        EstadoJogador estado = new EstadoJogador(heroi, baralho, 0, entrada);

        // Escolha narrativa: Altar Misterioso
        Escolha altar = new Escolha(
            "Altar Misterioso",
            "Você encontra um altar coberto de runas. Uma voz ecoa: 'Escolha com sabedoria...'"
        );
        altar.AdicionarOpcao(new OpcaoEscolha(
            "Oferecer sangue (-8 HP, +40 ouro)",
            e -> {
                e.getHeroi().ReceberDano(8);
                e.AdicionarOuro(40);
                System.out.println("Você se corta e sangra no altar. Moedas surgem do nada...");
                System.out.println("HP: " + e.getHeroi().getVida() + "/" + e.getHeroi().getVidaMaxima());
            }
        ));
        altar.AdicionarOpcao(new OpcaoEscolha(
            "Tocar a runa central (receber uma carta aleatória)",
            e -> {
                Carta carta = PoolCartas.CartaAleatoria();
                e.getBaralho().AdicionarCarta(carta);
                System.out.println("A runa brilha e uma carta materializa em suas mãos: " + carta.getNome() + "!");
            }
        ));
        altar.AdicionarOpcao(new OpcaoEscolha(
            "Seguir em frente (nada acontece)",
            e -> System.out.println("Você passa pelo altar sem olhar para trás.")
        ));

        // Monta a árvore do mapa
        NoMapa n0  = new NoMapa(new Batalha(new Inimigo("Rato Gigante", 25, 5)));
        NoMapa n1a = new NoMapa(altar);
        NoMapa n1b = new NoMapa(new EventoLoja());
        NoMapa n2a = new NoMapa(new Batalha(new Inimigo("Goblin Feroz", 40, 9)));
        NoMapa n2b = new NoMapa(new EventoFogueira());
        NoMapa n3a = new NoMapa(new Batalha(new Inimigo("Dragão", 60, 14)));   // final
        NoMapa n3b = new NoMapa(new Batalha(new Inimigo("Lich das Sombras", 55, 12))); // final

        n0.AdicionarFilho(n1a);  n0.AdicionarFilho(n1b);
        n1a.AdicionarFilho(n2a); n1b.AdicionarFilho(n2b);
        n2a.AdicionarFilho(n3a); n2b.AdicionarFilho(n3b);

        Mapa mapa = new Mapa(n0);
        NoMapa atual = mapa.getRaiz();
        atual.setVisitado(true);

        System.out.println("=== BEM-VINDO AO RPG GAME ===");
        System.out.println("Traversse o mapa e derrote o chefe final!");
        System.out.println();

        // Loop principal de progressão
        while (true) {
            atual.getEvento().Iniciar(estado);

            if (!heroi.EstaVivo()) {
                System.out.println(">>> GAME OVER! " + heroi.getNome() + " foi derrotado. <<<");
                break;
            }

            if (atual.isFinal()) {
                System.out.println(">>> VOCÊ VENCEU! Parabéns! <<<");
                break;
            }

            // Exibe status e deixa jogador escolher próximo nó
            ArrayList<NoMapa> filhos = atual.getFilhos();
            System.out.println("--- " + heroi.getNome() + ": " + heroi.getVida() + "/"
                    + heroi.getVidaMaxima() + " HP | " + estado.getOuro() + " ouro ---");
            System.out.println("Escolha o próximo destino:");
            for (int i = 0; i < filhos.size(); i++) {
                System.out.println("  " + (i + 1) + " - " + filhos.get(i).getEvento().getNome());
            }
            System.out.print("Escolha: ");

            int escolha = entrada.nextInt();
            while (escolha < 1 || escolha > filhos.size()) {
                System.out.print("Opção inválida: ");
                escolha = entrada.nextInt();
            }

            mapa.Avancar(filhos.get(escolha - 1));
            atual = mapa.getAtual();
            System.out.println();
        }

        entrada.close();
    }

    /** Monta o baralho inicial do jogador. */
    private static Baralho MontarBaralho() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 6));
        b.AdicionarCarta(new CartaDano("Espada", 1, 6));
        b.AdicionarCarta(new CartaDano("Bola de fogo", 2, 8));
        b.AdicionarCarta(new CartaDano("Espinho", 2, 8));
        b.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        b.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        b.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        b.AdicionarCarta(new CartaEscudo("Barreira", 2, 10));
        b.AdicionarCarta(new CartaEscudo("Parede de terra", 1, 10));
        b.AdicionarCarta(new CartaEscudo("Parede de ferro", 2, 15));
        b.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        b.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        b.AdicionarCarta(new CartaFraqueza("Golpe Enfraquecedor", 1, 2));
        b.AdicionarCarta(new CartaFraqueza("Golpe Atordoante", 1, 2));
        b.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        return b;
    }
}
