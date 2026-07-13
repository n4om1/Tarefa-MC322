import java.util.Random;

/**
 * Classe utilitária que gera cartas aleatórias a partir do conjunto de cartas disponíveis no jogo.
 * Utilizada por recompensas de batalha, lojas e eventos.
 */
public class PoolCartas {

    private static final Random rand = new Random();

    /** Construtor privado — classe utilitária, não deve ser instanciada. */
    private PoolCartas() {}

    /**
     * Retorna uma carta aleatória do pool disponível.
     * @return Uma nova instância de {@link Carta} escolhida aleatoriamente.
     */
    public static Carta CartaAleatoria() {
        int tipo = rand.nextInt(6);
        switch (tipo) {
            case 0: return new CartaDano("Espada", 1, 6);
            case 1: return new CartaDano("Flechada", 2, 12);
            case 2: return new CartaDano("Bola de fogo", 2, 8);
            case 3: return new CartaEscudo("Barreira", 2, 10);
            case 4: return new CartaVeneno("Frasco de Veneno", 1, 3);
            case 5: return new CartaFraqueza("Golpe Enfraquecedor", 1, 2);
            default: return new CartaDano("Espada", 1, 6);
        }
    }
}
