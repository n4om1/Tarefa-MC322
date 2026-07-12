import java.util.Scanner;

/**
 * Encapsula a lógica de um combate individual dentro do sistema de progressão do mapa.
 * Cada {@link NoMapa} contém uma instância de Batalha que é executada quando o jogador
 * visita aquele nó. A classe cuida de preparar o estado do herói antes do combate
 * e de reportar o resultado ao sistema de mapa.
 */
public class Batalha {

    /** O inimigo que o jogador enfrentará nesta batalha. */
    private Inimigo inimigo;

    /**
     * Construtor da batalha.
     * @param inimigo O inimigo que participará do combate.
     */
    public Batalha(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    /**
     * Prepara e executa o combate entre o herói e o inimigo.
     * Antes de iniciar, limpa os efeitos ativos do herói e reinicia o baralho,
     * mantendo apenas os pontos de vida e a composição do baralho entre batalhas.
     *
     * @param heroi   O herói controlado pelo jogador (vida e baralho são mantidos).
     * @param baralho O baralho do herói (todas as cartas são devolvidas à pilha de compra).
     * @param entrada O scanner para leitura das escolhas do jogador.
     * @return {@code true} se o herói venceu; {@code false} se foi derrotado.
     */
    public boolean Executar(Heroi heroi, Baralho baralho, Scanner entrada) {
        // Reseta efeitos e baralho entre batalhas, mantendo vida
        heroi.LimparEfeitos();
        baralho.ReiniciarParaNovaBatalha();

        System.out.println(">>> Batalha contra " + inimigo.getNome() + " (" + inimigo.getVida() + " HP) <<<");
        System.out.println();

        Combate combate = new Combate(heroi, inimigo, baralho, entrada);
        combate.Executar();

        return heroi.EstaVivo();
    }

    /** @return O inimigo desta batalha. */
    public Inimigo getInimigo() { return inimigo; }
}