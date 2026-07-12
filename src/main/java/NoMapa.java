import java.util.ArrayList;

/**
 * Representa um nó na estrutura de árvore do mapa do jogo.
 * Cada nó contém uma batalha a ser disputada e referências para os próximos
 * nós disponíveis ao jogador após a vitória.
 */
public class NoMapa {

    /** A batalha associada a este nó do mapa. */
    private Batalha batalha;

    /** Lista de nós filhos (próximas batalhas disponíveis ao avançar). */
    private ArrayList<NoMapa> filhos;

    /** Indica se o jogador já visitou este nó. */
    private boolean visitado;

    /**
     * Construtor do nó do mapa.
     * @param batalha A batalha que ocorrerá neste nó.
     */
    public NoMapa(Batalha batalha) {
        this.batalha = batalha;
        this.filhos = new ArrayList<>();
        this.visitado = false;
    }

    /**
     * Adiciona um nó filho (próximo possível destino após vencer este nó).
     * @param filho O nó a ser adicionado como próximo destino.
     */
    public void AdicionarFilho(NoMapa filho) {
        filhos.add(filho);
    }

    /**
     * Verifica se este é um nó final (sem filhos), indicando o fim do mapa.
     * @return {@code true} se não houver nós filhos; {@code false} caso contrário.
     */
    public boolean isFinal() {
        return filhos.isEmpty();
    }

    /** @return A batalha deste nó. */
    public Batalha getBatalha() { return batalha; }

    /** @return A lista de nós filhos disponíveis. */
    public ArrayList<NoMapa> getFilhos() { return filhos; }

    /** @return Se o nó foi visitado. */
    public boolean isVisitado() { return visitado; }

    /**
     * Marca este nó como visitado.
     * @param visitado O novo estado de visita.
     */
    public void setVisitado(boolean visitado) { this.visitado = visitado; }
}