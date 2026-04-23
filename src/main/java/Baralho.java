import java.util.ArrayList;
import java.util.Collections;

/**
 * Gerencia o ciclo de vida das cartas durante uma partida.
 * Esta classe controla a movimentação de objetos {@link Carta} entre as três 
 * coleções principais: pilha de compra, mão do jogador e pilha de descarte.
 */
public class Baralho {

    /** Lista que representa as cartas disponíveis para serem compradas. */
    private ArrayList<Carta> pilhaCompra;

    /** Lista que contém as cartas que podem ser jogadas no turno atual. */
    private ArrayList<Carta> maoJogador;

    /** Lista para onde as cartas são enviadas após o uso ou fim do turno. */
    private ArrayList<Carta> pilhaDescarte;

    /**
     * Construtor padrão. Inicializa as listas de compra, mão e descarte 
     * como listas vazias.
     */
    public Baralho() {
        pilhaCompra = new ArrayList<>();
        maoJogador = new ArrayList<>();
        pilhaDescarte = new ArrayList<>();
    }

    /**
     * Adiciona uma nova carta à base do baralho (pilha de compra).
     * 
     * @param carta O objeto {@link Carta} a ser inserido no jogo.
     */
    public void AdicionarCarta(Carta carta) {
        pilhaCompra.add(carta);
    }

    /**
     * Reordena aleatoriamente as cartas presentes na pilha de compra.
     */
    public void Embaralhar() {
        Collections.shuffle(pilhaCompra);
    }

    /**
     * Transfere cartas da pilha de compra para a mão do jogador.
     * Caso a pilha de compra fique vazia durante o processo, a pilha de descarte
     * é movida para a compra e embaralhada automaticamente.
     * @param quantidade O número de cartas a serem sacadas.
     */
    public void ComprarCartas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (pilhaCompra.isEmpty()) {
                if (pilhaDescarte.isEmpty()) {
                    System.out.println("Não há mais cartas disponíveis no baralho!");
                    break;
                }
                pilhaCompra.addAll(pilhaDescarte);
                pilhaDescarte.clear();
                Collections.shuffle(pilhaCompra);
                System.out.println("Pilha de compra esgotada! Embaralhando pilha de descarte...");
            }
            maoJogador.add(pilhaCompra.remove(0));
        }
    }

    /**
     * Remove uma carta específica da mão e a envia para a pilha de descarte.
     * @param indice A posição da carta na lista da mão (começando em 0).
     * @throws IndexOutOfBoundsException Se o índice fornecido for inválido.
     */
    public void DescartarCarta(int indice) {
        pilhaDescarte.add(maoJogador.remove(indice));
    }

    /**
     * Limpa a mão do jogador, movendo todas as cartas atuais para o descarte.
     * Geralmente chamado ao final de cada turno.
     */
    public void DescartarMao() {
        pilhaDescarte.addAll(maoJogador);
        maoJogador.clear();
    }

    /**
     * Obtém a lista de cartas que o jogador possui atualmente.
     * @return {@code ArrayList<Carta>} representando a mão.
     */
    public ArrayList<Carta> getMao() { return maoJogador; }

    /**
     * Retorna a quantidade de cartas restantes para compra.
     * @return O tamanho da {@code pilhaCompra}.
     */
    public int getTamanhoCompra() { return pilhaCompra.size(); }

    /**
     * Retorna a quantidade de cartas que já foram descartadas.
     * @return O tamanho da {@code pilhaDescarte}.
     */
    public int getTamanhoDescarte() { return pilhaDescarte.size(); }

    /**
     * Verifica se o jogador possui cartas na mão.
     * @return {@code true} se a mão estiver vazia; {@code false} caso contrário.
     */
    public boolean MaoVazia() { return maoJogador.isEmpty(); }
}
