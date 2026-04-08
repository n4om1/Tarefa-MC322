public class Heroi extends Entidade {

    private int energiaMaxima;
    private int energiaAtual;

    public Heroi(String nome, int vida, int energiaMaxima) {
        super(nome, vida);
        this.energiaMaxima = energiaMaxima;
        this.energiaAtual = energiaMaxima;
    }

    // Restaura energia ao máximo (chamado no início de cada turno)
    public void RecuperarEnergia() {
        energiaAtual = energiaMaxima;
    }

    // Tenta gastar energia; retorna false se não houver energia suficiente
    public boolean GastarEnergia(int custo) {
        if (energiaAtual >= custo) {
            energiaAtual -= custo;
            return true;
        }
        return false;
    }

    public int getEnergiaAtual() { return energiaAtual; }
    public int getEnergiaMaxima() { return energiaMaxima; }
}