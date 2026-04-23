import java.util.Scanner;

/**
 * Classe principal que serve como ponto de entrada para a aplicação.
 * Responsável por inicializar os componentes do jogo, configurar o baralho
 * inicial e disparar o loop de execução do combate.
 */
public class App {

    /**
     * Construtor privado para evitar a instanciação da classe utilitária.
     * Como a classe App contém apenas o método main, não há necessidade de instanciá-la.
     */
    private App() {
        // Construtor privado para esconder o implícito
    }

    /**
     * Método principal que coordena a configuração inicial do jogo.
     * O fluxo segue os seguintes passos:
     * <ol>
     * <li>Instancia os personagens (Heroi e Inimigo).</li>
     * <li>Configura o {@link Baralho} com diferentes tipos de {@link Carta}.</li>
     * <li>Embaralha as cartas para garantir aleatoriedade.</li>
     * <li>Instancia e executa o {@link Combate}, que gerencia o fluxo de turnos.</li>
     * </ol>
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
        public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        // Instancia o herói e o inimigo
        Heroi heroi = new Heroi("Herói", 40, 3);
        Inimigo inimigo = new Inimigo("Rato Gigante", 30, 7);

        // Monta o baralho com cartas variadas, incluindo cartas de efeito
        Baralho baralho = new Baralho();
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaEscudo("Barreira", 2, 10));
        baralho.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        baralho.AdicionarCarta(new CartaVeneno("Frasco de Veneno", 1, 3));
        baralho.AdicionarCarta(new CartaFraqueza("Golpe Enfraquecedor", 1, 2));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        baralho.Embaralhar();

        System.out.println("Prepare-se para batalhar!");

        /** * O objeto Combate atua como o motor do jogo (Publisher),
         * gerenciando a interação entre herói, inimigo e baralho.
         */
        Combate combate = new Combate(heroi, inimigo, baralho, entrada);
        combate.Executar();

        entrada.close();
    }
}
