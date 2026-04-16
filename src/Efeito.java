public abstract class Efeito implements Subscriber {

    private String nome;
    private Entidade dono;
    private int acumulos;

    public Efeito(String nome, Entidade dono, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
    }

    // Cada efeito define como reage aos eventos do combate
    public abstract void serNotificado(String evento, Combate combate);

    // Retorna uma String representando o efeito com nome e acúmulos
    public String getString() {
        return nome + "(" + acumulos + ")";
    }

    public void AdicionarAcumulos(int quantidade) {
        acumulos += quantidade;
    }

    public void RemoverAcumulos(int quantidade) {
        acumulos -= quantidade;
    }

    public String getNome() { return nome; }
    public Entidade getDono() { return dono; }
    public int getAcumulos() { return acumulos; }
}
