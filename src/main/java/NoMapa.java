import java.util.ArrayList;

/**
 * Representa um nó na estrutura de árvore do mapa do jogo.
 * Cada nó contém um {@link Evento} (batalha, loja, fogueira, escolha, etc.)
 * e referências para os próximos nós disponíveis ao jogador após a conclusão do evento.
 */
public class NoMapa {

    private Evento evento;
    private ArrayList<NoMapa> filhos;
    private boolean visitado;

    /**
     * @param evento O evento associado a este nó do mapa.
     */
    public NoMapa(Evento evento) {
        this.evento = evento;
        this.filhos = new ArrayList<>();
        this.visitado = false;
    }

    /** Adiciona um nó filho (próximo destino disponível após concluir este nó). */
    public void AdicionarFilho(NoMapa filho) {
        filhos.add(filho);
    }

    /**
     * Verifica se este é um nó final (sem filhos), indicando o fim do mapa.
     * @return true se não houver nós filhos.
     */
    public boolean isFinal() { return filhos.isEmpty(); }

    public Evento getEvento() { return evento; }
    public ArrayList<NoMapa> getFilhos() { return filhos; }
    public boolean isVisitado() { return visitado; }
    public void setVisitado(boolean visitado) { this.visitado = visitado; }
}
