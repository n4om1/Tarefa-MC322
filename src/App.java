import java.util.Scanner;

public class App {
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

        // Combate atua como Publisher; delega todo o fluxo de batalha
        Combate combate = new Combate(heroi, inimigo, baralho, entrada);
        combate.Executar();

        entrada.close();
    }
}
