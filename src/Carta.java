public abstract class Carta {

    private String nome;
    private String descricao;
    private int custo;

    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    // Executa o efeito da carta; recebe o combate para que cartas de efeito possam inscrever subscribers
    public abstract void Usar(Heroi heroi, Inimigo inimigo, Combate combate);

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getCusto() { return custo; }

    public String Descricao() {
        return nome + " [Custo: " + custo + "] - " + descricao;
    }
}
