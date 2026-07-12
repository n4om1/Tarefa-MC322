import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal que serve como ponto de entrada para a aplicação.
 * Configura o mapa de batalhas e gerencia a progressão do jogador
 * pela campanha, mantendo o estado entre combates.
 */
public class App {

    /** Construtor privado — classe utilitária, não deve ser instanciada. */
    private App() {}

    /**
     * Método principal que inicializa o herói, o baralho e o mapa do jogo,
     * e então executa o loop de progressão entre batalhas.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Instancia o herói — vida e baralho são mantidos entre batalhas
        Heroi heroi = new Heroi("Herói", 40, 3);

        // Monta o baralho inicial
        Baralho baralho = new Baralho();
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Bola de fogo", 2, 8));
        baralho.AdicionarCarta(new CartaDano("Espinho", 2, 8));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaEscudo("Barreira", 2, 10));
        baralho.AdicionarCarta(new CartaEscudo("Parede de terra", 1, 10));
        baralho.AdicionarCarta(new CartaEscudo("Parede de ferro", 2, 15));
        baralho.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        baralho.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        baralho.AdicionarCarta(new CartaFraqueza("Golpe Enfraquecedor", 1, 2));
        baralho.AdicionarCarta(new CartaFraqueza("Golpe Atordoante", 1, 2));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));

        // Monta o mapa como uma árvore de batalhas
        //
        //  [Rato] --> [Goblin] --> [Dragão (final)]
        //         --> [Lobisomem] --> [Lich (final)]
        //
        NoMapa no0 = new NoMapa(new Batalha(new Inimigo("Rato Gigante", 25, 5)));
        NoMapa no1 = new NoMapa(new Batalha(new Inimigo("Goblin", 35, 8)));
        NoMapa no2 = new NoMapa(new Batalha(new Inimigo("Lobisomem", 40, 10)));
        NoMapa no3 = new NoMapa(new Batalha(new Inimigo("Dragão", 60, 14)));
        NoMapa no4 = new NoMapa(new Batalha(new Inimigo("Lich", 55, 12)));

        no0.AdicionarFilho(no1);
        no0.AdicionarFilho(no2);
        no1.AdicionarFilho(no3);
        no2.AdicionarFilho(no4);

        Mapa mapa = new Mapa(no0);
        NoMapa atual = mapa.getRaiz();
        atual.setVisitado(true);

        System.out.println("=== BEM-VINDO AO RPG GAME ===");
        System.out.println("Sua missão: atravessar o mapa e derrotar o chefe final!");
        System.out.println();

        // Loop principal de progressão
        while (true) {
            // Executa a batalha do nó atual
            boolean vitoria = atual.getBatalha().Executar(heroi, baralho, entrada);

            if (!vitoria) {
                System.out.println(">>> GAME OVER! " + heroi.getNome() + " foi derrotado. <<<");
                break;
            }

            // Vitória em nó final = fim do jogo
            if (atual.isFinal()) {
                System.out.println(">>> VOCÊ VENCEU O JOGO! Parabéns! <<<");
                break;
            }

            // Exibe status do herói entre batalhas
            System.out.println();
            System.out.println("--- Status do herói: " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " HP ---");

            // Mostra opções de próxima batalha
            ArrayList<NoMapa> filhos = atual.getFilhos();
            System.out.println("Escolha o próximo destino:");
            for (int i = 0; i < filhos.size(); i++) {
                Inimigo prox = filhos.get(i).getBatalha().getInimigo();
                System.out.println("  " + (i + 1) + " - " + prox.getNome() + " (" + prox.getVida() + " HP)");
            }
            System.out.print("Escolha: ");

            int escolha = entrada.nextInt();
            while (escolha < 1 || escolha > filhos.size()) {
                System.out.print("Opção inválida. Escolha novamente: ");
                escolha = entrada.nextInt();
            }

            mapa.Avancar(filhos.get(escolha - 1));
            atual = mapa.getAtual();
            System.out.println();
        }

        entrada.close();
    }
}