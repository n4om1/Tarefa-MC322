import java.util.ArrayList;
import java.util.Scanner;

/**
 * Motor principal do jogo que gerencia o fluxo de combate entre o Herói e o Inimigo.
 * Esta classe atua como um {@link Publisher} no padrão Observer, notificando 
 * interessados sobre eventos críticos como ataques e encerramento de turnos.
 */
public class Combate extends Publisher {

    /** O protagonista controlado pelo jogador. */
    private Heroi heroi;
    
    /** O adversário controlado pela lógica automática. */
    private Inimigo inimigo;
    
    /** O gerenciador de cartas disponível para o herói. */
    private Baralho baralho;
    
    /** Leitor de entrada para capturar as escolhas do jogador. */
    private Scanner entrada;

    /** Bônus de dano calculado dinamicamente por efeitos (Força, Fraqueza, etc). */
    private int danoBonus;
    
    /** Referência para a entidade que está realizando a ação ofensiva no momento. */
    private Entidade atacante;

    /**
     * Inicializa uma nova instância de combate.
     * @param heroi O herói participante.
     * @param inimigo O inimigo participante.
     * @param baralho O baralho configurado para a partida.
     * @param entrada O scanner para leitura de dados do console.
     */
    public Combate(Heroi heroi, Inimigo inimigo, Baralho baralho, Scanner entrada) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.baralho = baralho;
        this.entrada = entrada;
        this.danoBonus = 0;
    }

    /**
     * Notifica os observadores de que um ataque está prestes a ocorrer.
     * Reinicia o bônus de dano e permite que efeitos inscritos (como Força ou Fraqueza) 
     * modifiquem o valor final do dano através do método {@link #AddDanoBonus(int)}.
     * @param atacante A {@link Entidade} que está desferindo o golpe.
     */
    public void NotificarAtaque(Entidade atacante) {
        this.atacante = atacante;
        this.danoBonus = 0;
        notificar("ATAQUE", this);
    }

    /** 
     * Entidade que está atacando
     * 
     * @return A entidade que está atacando no momento. */
    public Entidade getAtacante() { return atacante; }

    /**
     * Adiciona um valor ao modificador de dano do ataque atual.
     * @param bonus Valor (positivo ou negativo) a ser somado ao dano base.
     */
    public void AddDanoBonus(int bonus) { danoBonus += bonus; }

    /** 
     * Danos acumulados pelos efeitos
     * @return O modificador total de dano acumulado pelos efeitos. */
    public int getDanoBonus() { return danoBonus; }

    /**
     * Inicia o loop principal da batalha.
     * O combate continua até que uma das entidades tenha seus pontos de vida zerados.
     * Gerencia a sequência: Início de Turno -> Turno do Jogador -> Turno do Inimigo.
     */
    public void Executar() {
        int turno = 1;

        while (heroi.EstaVivo() && inimigo.EstaVivo()) {
            System.out.println("=================");
            System.out.println("  TURNO " + turno);
            System.out.println("=================");

            // Início do turno: recupera energia, zera escudo e compra cartas
            heroi.RecuperarEnergia();
            heroi.ZerarEscudo();
            baralho.ComprarCartas(5);

            // Exibe status com efeitos ativos
            System.out.println(heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima()
                    + " de vida | " + heroi.getEscudo() + " de escudo" + heroi.getEfeitosString());
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima()
                    + " de vida" + inimigo.getEfeitosString());
            System.out.println("[!] " + inimigo.AnunciarIntencao());
            System.out.println("Compra: " + baralho.getTamanhoCompra()
                    + " | Descarte: " + baralho.getTamanhoDescarte());
            System.out.println();

            TurnoJogador();

            // Notifica fim do turno do jogador (efeitos como Veneno reagem aqui)
            notificar("FIM_TURNO_JOGADOR", this);
            System.out.println();

            if (!inimigo.EstaVivo()) break;

            TurnoInimigo();

            System.out.println();
            turno++;
        }

        // Resultado final
        System.out.println("=================");
        if (!inimigo.EstaVivo()) {
            System.out.println("Vitória! " + inimigo.getNome() + " foi derrotado!");
        } else {
            System.out.println("Derrota! " + heroi.getNome() + " foi derrotado...");
        }
        System.out.println("=================");
    }

    /**
     * Gerencia a fase de decisão do jogador.
     * Permite o uso de cartas enquanto houver energia disponível e cartas na mão.
     */
    private void TurnoJogador() {
        while (heroi.getEnergiaAtual() > 0 && !baralho.MaoVazia() && inimigo.EstaVivo()) {
            ArrayList<Carta> mao = baralho.getMao();

            System.out.println("--- Energia: " + heroi.getEnergiaAtual() + "/" + heroi.getEnergiaMaxima() + " ---");
            System.out.println("Sua mão:");
            for (int i = 0; i < mao.size(); i++) {
                System.out.println("  " + (i + 1) + " - " + mao.get(i).Descricao());
            }
            System.out.println("  0 - Encerrar turno");
            System.out.print("Escolha: ");

            int escolha = entrada.nextInt();
            if (escolha == 0) break;

            if (escolha < 1 || escolha > mao.size()) {
                System.out.println("Opção inválida.");
                continue;
            }

            Carta cartaEscolhida = mao.get(escolha - 1);

            if (!heroi.GastarEnergia(cartaEscolhida.getCusto())) {
                System.out.println("Energia insuficiente para usar " + cartaEscolhida.getNome() + "!");
                continue;
            }

            cartaEscolhida.Usar(heroi, inimigo, this);
            baralho.DescartarCarta(escolha - 1);
            System.out.println();

            if (!inimigo.EstaVivo()) break;
        }

        // Cartas não jogadas vão para o descarte
        if (!baralho.MaoVazia()) {
            System.out.println("Cartas restantes na mão vão para o descarte.");
            baralho.DescartarMao();
        }
    }

    /**
     * Gerencia a fase de ação do inimigo.
     * Delega ao objeto {@link Inimigo} a execução de sua lógica interna.
     */ 
    private void TurnoInimigo() {
        System.out.println("--- Vez do " + inimigo.getNome() + " ---");
        inimigo.ExecutarAcao(heroi, this);
    }
}
