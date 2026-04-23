/**
 * Representa o protagonista do jogo, controlado pelo jogador.
 * Esta classe estende {@link Entidade} e adiciona o gerenciamento de energia,
 * necessário para a execução de habilidades e ações durante o combate.
 */
public class Heroi extends Entidade {

    /** A quantidade máxima de energia que o herói pode possuir. */
    private int energiaMaxima;

    /** A quantidade de energia disponível no momento atual. */
    private int energiaAtual;

    /**
     * Construtor para criar um novo herói.
     * Ao ser criado, a energia atual é definida como a energia máxima.
     *
     * @param nome O nome do herói.
     * @param vida A quantidade inicial de pontos de vida.
     * @param energiaMaxima A capacidade total de energia do herói.
     */
    public Heroi(String nome, int vida, int energiaMaxima) {
        super(nome, vida);
        this.energiaMaxima = energiaMaxima;
        this.energiaAtual = energiaMaxima;
    }

    /**
     * Restaura a energia atual do herói para o seu valor máximo.
     * Geralmente utilizado no processamento de início de turno.
     */
    public void RecuperarEnergia() {
        energiaAtual = energiaMaxima;
    }

    /**
     * Tenta subtrair um valor de custo da energia atual do herói.
     * Caso o herói não possua energia suficiente, a operação é cancelada.
     *
     * @param custo A quantidade de energia necessária para realizar a ação.
     * @return {@code true} se o herói possuía energia suficiente e o gasto foi realizado; 
     * {@code false} caso contrário.
     */    
    public boolean GastarEnergia(int custo) {
        if (energiaAtual >= custo) {
            energiaAtual -= custo;
            return true;
        }
        return false;
    }

    /**
     * Obtém o nível de energia disponível no momento.
     * @return O valor da energia atual.
     */
    public int getEnergiaAtual() { return energiaAtual; }

    /**
     * Obtém o limite máximo de energia que o herói pode atingir.
     * @return O valor da energia máxima.
     */
    public int getEnergiaMaxima() { return energiaMaxima; }
}