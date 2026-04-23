/**
 * Classe base abstrata para todos os modificadores de estado (buffs e debuffs).
 * Implementa a interface {@link Subscriber} para permitir que o efeito reaja 
 * dinamicamente a eventos disparados durante o {@link Combate}.
 * * <p>Exemplos de implementação incluem Veneno, Força e Fraqueza.</p>
 */
public abstract class Efeito implements Subscriber {

    /** O nome identificador do efeito (ex: "Veneno", "Força"). */
    private String nome;

    /** A {@link Entidade} que está sob a influência deste efeito. */
    private Entidade dono;

    /** * A intensidade ou duração do efeito. 
     * O significado exato varia conforme a implementação da subclasse.
     */
    private int acumulos;

    /**
     * Construtor para criar um novo efeito.
     *
     * @param nome O nome do efeito.
     * @param dono A entidade que receberá o efeito.
     * @param acumulos A quantidade inicial de acúmulos/intensidade.
     */
    public Efeito(String nome, Entidade dono, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
    }

    /**
     * Define a lógica de reação do efeito quando um evento ocorre no combate.
     * Este método é chamado pelo {@link Publisher} sempre que uma notificação relevante é disparada.
     *
     * @param evento O tipo de evento ocorrido (ex: "ATAQUE", "FIM_TURNO_JOGADOR").
     * @param combate A instância do combate atual para consulta ou alteração de estado.
     */
    @Override
    public abstract void serNotificado(String evento, Combate combate);

    /**
     * Gera uma representação textual do efeito para exibição na interface.
     * Exemplo: "Veneno(3)"
     * @return Uma String formatada com o nome e a quantidade de acúmulos.
     */
    public String getString() {
        return nome + "(" + acumulos + ")";
    }

    /**
     * Incrementa a intensidade do efeito.
     * @param quantidade Valor a ser adicionado aos acúmulos atuais.
     */
    public void AdicionarAcumulos(int quantidade) {
        acumulos += quantidade;
    }

    /**
     * Decrementa a intensidade do efeito.
     * @param quantidade Valor a ser subtraído dos acúmulos atuais.
     */
    public void RemoverAcumulos(int quantidade) {
        acumulos -= quantidade;
    }

    /** 
     * Nome do efeito
     * 
     * @return O nome do efeito. */
    public String getNome() { return nome; }

    /** 
     * Entidade dona do efeito
     * 
     * @return A entidade que possui o efeito. */
    public Entidade getDono() { return dono; }

    /** 
     * Acúmulo total atual
     * 
     * @return A quantidade atual de acúmulos. */
    public int getAcumulos() { return acumulos; }
}
