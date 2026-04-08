public public abstract class Carta {

    private String nome;
    private String descricao;
    private int custo;

    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    // Executa o efeito da carta
    public abstract void Usar(Heroi heroi, Inimigo inimigo);

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getCusto() { return custo; }

    public String Descricao() {
        return nome + " [Custo: " + custo + "] - " + descricao;
    }
} {
    
}
