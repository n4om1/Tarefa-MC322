import java.util.Random;
import java.util.Scanner;

/**
 * Evento de combate entre o herói e um inimigo.
 * Herda de {@link Evento} e integra-se ao sistema de mapa.
 * Após uma vitória, o jogador recebe ouro e pode escolher uma nova carta.
 */
public class Batalha extends Evento {

    private Inimigo inimigo;
    private final Random rand = new Random();

    /**
     * @param inimigo O inimigo que o jogador enfrentará nesta batalha.
     */
    public Batalha(Inimigo inimigo) {
        super("Batalha: " + inimigo.getNome());
        this.inimigo = inimigo;
    }

    /**
     * Prepara e executa o combate. Limpa efeitos e reinicia o baralho antes de iniciar,
     * mantendo apenas vida e composição do deck. Se o herói vencer, distribui recompensas.
     * @param estado O estado do jogador mantido entre batalhas.
     */
    @Override
    public void Iniciar(EstadoJogador estado) {
        Heroi heroi = estado.getHeroi();
        Baralho baralho = estado.getBaralho();
        Scanner entrada = estado.getEntrada();

        // Reseta efeitos e baralho entre batalhas, mantendo vida e composição do deck
        heroi.LimparEfeitos();
        baralho.ReiniciarParaNovaBatalha();

        System.out.println(">>> Batalha contra " + inimigo.getNome()
                + " (" + inimigo.getVida() + " HP) <<<");
        System.out.println();

        Combate combate = new Combate(heroi, inimigo, baralho, entrada);
        combate.Executar();

        if (heroi.EstaVivo()) {
            DistribuirRecompensas(estado);
        }
    }

    /**
     * Distribui recompensas após a vitória: ouro e escolha de carta.
     */
    private void DistribuirRecompensas(EstadoJogador estado) {
        int ouroGanho = 20 + rand.nextInt(21); // 20–40 ouro
        estado.AdicionarOuro(ouroGanho);
        System.out.println("Recompensa: +" + ouroGanho + " ouro! Total: " + estado.getOuro() + " ouro.");
        System.out.println();

        System.out.println("Escolha uma carta para adicionar ao baralho (0 para pular):");
        Carta[] opcoes = new Carta[3];
        for (int i = 0; i < 3; i++) {
            opcoes[i] = PoolCartas.CartaAleatoria();
            System.out.println("  " + (i + 1) + " - " + opcoes[i].Descricao());
        }
        System.out.println("  0 - Pular");
        System.out.print("Escolha: ");

        int escolha = estado.getEntrada().nextInt();
        if (escolha >= 1 && escolha <= 3) {
            estado.getBaralho().AdicionarCarta(opcoes[escolha - 1]);
            System.out.println(opcoes[escolha - 1].getNome() + " adicionada ao baralho!");
        } else {
            System.out.println("Carta pulada.");
        }
        System.out.println();
    }

    public Inimigo getInimigo() { return inimigo; }
}
