import java.util.Scanner;

/**
 * Encapsula o estado persistente do jogador entre batalhas e eventos.
 * Agrupa herói, baralho, ouro e o scanner de entrada em um único objeto
 * que é passado para todos os {@link Evento}s do mapa.
 */
public class EstadoJogador {

    private Heroi heroi;
    private Baralho baralho;
    private int ouro;
    private Scanner entrada;

    /**
     * @param heroi        O herói controlado pelo jogador.
     * @param baralho      O baralho do jogador.
     * @param ouroInicial  Ouro inicial (normalmente 0).
     * @param entrada      Scanner para leitura de input do terminal.
     */
    public EstadoJogador(Heroi heroi, Baralho baralho, int ouroInicial, Scanner entrada) {
        this.heroi = heroi;
        this.baralho = baralho;
        this.ouro = ouroInicial;
        this.entrada = entrada;
    }

    /** Adiciona ouro ao total do jogador. */
    public void AdicionarOuro(int quantidade) { ouro += quantidade; }

    /**
     * Tenta gastar ouro. Retorna false e não altera o valor se saldo insuficiente.
     * @return true se o gasto foi realizado com sucesso.
     */
    public boolean GastarOuro(int quantidade) {
        if (ouro >= quantidade) {
            ouro -= quantidade;
            return true;
        }
        return false;
    }

    public Heroi getHeroi() { return heroi; }
    public Baralho getBaralho() { return baralho; }
    public int getOuro() { return ouro; }
    public Scanner getEntrada() { return entrada; }
}
