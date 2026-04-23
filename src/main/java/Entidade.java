import java.util.ArrayList;

/**
 * Classe abstrata base que define os atributos e comportamentos comuns a todos 
 * os seres combatentes no jogo (Heróis e Inimigos).
 * Gerencia o estado vital, sistemas de defesa (escudo) e a lista de efeitos 
 * reativos aplicados à entidade.
 */
public abstract class Entidade {

    /** O nome identificador da entidade. */
    private String nome;

    /** Os pontos de vida atuais da entidade. */
    private int vida;

    /** O limite máximo de pontos de vida que esta entidade pode possuir. */
    private int vidaMaxima;

    /** Pontos de defesa temporários que absorvem dano antes de afetar a vida. */
    private int escudo;

    /** Lista de modificadores de estado (buffs/debuffs) ativos na entidade. */
    private ArrayList<Efeito> efeitos;

    /**
     * Construtor para inicializar uma entidade com vida plena e sem efeitos.
     * 
     * @param nome O nome da entidade.
     * @param vida O valor inicial e máximo de pontos de vida.
     */
    public Entidade(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.escudo = 0;
        this.efeitos = new ArrayList<>();
    }

    /**
     * Processa a redução de pontos de vida da entidade.
     * O cálculo prioriza a redução do escudo; apenas o excedente do dano 
     * que ultrapassar o valor do escudo é subtraído da vida atual.
     * 
     * @param dano O valor bruto de dano a ser aplicado.
     */
    public void ReceberDano(int dano) {
        int danoRestante = dano - escudo;
        escudo = Math.max(0, escudo - dano);
        if (danoRestante > 0) {
            vida = Math.max(0, vida - danoRestante);
        }
    }

    /**
     * Incrementa o valor de defesa temporária da entidade.
     * @param quantidade Valor a ser somado ao escudo atual.
     */
    public void GanharEscudo(int quantidade) {
        escudo += quantidade;
    }

    /**
     * Verifica se a entidade possui pontos de vida acima de zero.
     * @return {@code true} se a vida for maior que 0; {@code false} caso contrário.
     */
    public boolean EstaVivo() {
        return vida > 0;
    }

    /**
     * Adiciona ou atualiza um efeito na entidade.
     * Se um efeito com o mesmo nome já existir, os acúmulos são somados. 
     * Caso seja um efeito novo, ele é adicionado à lista e inscrito como 
     * um observador no objeto {@link Combate}.
     * @param efeito O efeito a ser aplicado.
     * @param combate A instância de combate onde o efeito será registrado como subscriber.
     */
    public void AplicarEfeito(Efeito efeito, Combate combate) {
        for (Efeito e : efeitos) {
            if (e.getNome().equals(efeito.getNome())) {
                e.AdicionarAcumulos(efeito.getAcumulos());
                System.out.println("[Efeito] " + getNome() + " agora tem " + e.getAcumulos() + " acúmulos de " + e.getNome() + ".");
                return;
            }
        }
        efeitos.add(efeito);
        combate.inscrever(efeito);
        System.out.println("[Efeito] " + getNome() + " foi afligido por " + efeito.getString() + ".");
    }

    /**
     * Remove um efeito específico da entidade.
     * Além de remover da lista interna, o efeito é desinscrito do sistema 
     * de notificações do combate.
     * @param efeito O objeto de efeito a ser removido.
     * @param combate A instância de combate para desinscrição do subscriber.
     */
    public void RemoverEfeito(Efeito efeito, Combate combate) {
        efeitos.remove(efeito);
        combate.desinscrever(efeito);
    }

    /**
     * Constrói uma representação textual formatada de todos os efeitos ativos.
     * Útil para exibição de status no console. Ex: " [Veneno(3), Força(2)]".
     * @return Uma String contendo a lista de efeitos ou uma string vazia se não houver nenhum.
     */ 
    public String getEfeitosString() {
        if (efeitos.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(" [");
        for (int i = 0; i < efeitos.size(); i++) {
            sb.append(efeitos.get(i).getString());
            if (i < efeitos.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Reseta os pontos de escudo para zero.
     * Geralmente invocado no início de cada turno para evitar acúmulo infinito de defesa.
     */
    public void ZerarEscudo() {
        escudo = 0;
    }

    /**
     * Nome da entidade.
     * 
     * @return O nome da entidade. */
    public String getNome() { return nome; }

    /** 
     * Vida atual.
     * 
     * @return O valor de vida atual. */
    public int getVida() { return vida; }

    /**
     * Limite máximo de vida da entidade.
     * 
     *  @return O limite máximo de vida definido na criação. */
    public int getVidaMaxima() { return vidaMaxima; }

    /**
     *  Valor de proteção atual.
     * 
     *  @return O valor atual de proteção (escudo). */
    public int getEscudo() { return escudo; }

    /**
     * Todos os efeitos aplicados a esta entidade.
     * 
     *  @return A lista de todos os efeitos aplicados a esta entidade. */
    public ArrayList<Efeito> getEfeitos() { return efeitos; }
}
