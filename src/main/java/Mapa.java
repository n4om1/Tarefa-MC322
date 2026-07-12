/**
 * Representa o mapa do jogo como uma estrutura em árvore de batalhas.
 * O jogador navega pelos nós do mapa, escolhendo sempre avançar para nós
 * não visitados mais profundos, até alcançar um nó final ou ser derrotado.
 */
public class Mapa {

    /** O nó raiz da árvore, onde o jogo começa. */
    private NoMapa raiz;

    /** O nó em que o jogador se encontra atualmente. */
    private NoMapa atual;

    /**
     * Construtor do mapa.
     * @param raiz O nó inicial do jogo.
     */
    public Mapa(NoMapa raiz) {
        this.raiz = raiz;
        this.atual = raiz;
    }

    /**
     * Avança o jogador para o próximo nó escolhido.
     * @param proximo O nó para o qual o jogador deseja se mover.
     */
    public void Avancar(NoMapa proximo) {
        atual = proximo;
        atual.setVisitado(true);
    }

    /**
     * Verifica se o jogador chegou a um nó final (sem filhos).
     * @return {@code true} se o nó atual for uma folha da árvore.
     */
    public boolean JogoFinalizado() {
        return atual.isFinal();
    }

    /** @return O nó raiz do mapa. */
    public NoMapa getRaiz() { return raiz; }

    /** @return O nó onde o jogador está atualmente. */
    public NoMapa getAtual() { return atual; }
}