import java.util.ArrayList;
import java.util.Scanner;

public class Combate extends Publisher {

    private Heroi heroi;
    private Inimigo inimigo;
    private Baralho baralho;
    private Scanner entrada;

    // Usados pelo padrão Observer para calcular dano de ataque com efeitos
    private int danoBonus;
    private Entidade atacante;

    public Combate(Heroi heroi, Inimigo inimigo, Baralho baralho, Scanner entrada) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.baralho = baralho;
        this.entrada = entrada;
        this.danoBonus = 0;
    }

    // Notifica efeitos sobre um ataque e registra o atacante para que efeitos como Força e Fraqueza possam reagir
    public void NotificarAtaque(Entidade atacante) {
        this.atacante = atacante;
        this.danoBonus = 0;
        notificar("ATAQUE", this);
    }

    public Entidade getAtacante() { return atacante; }
    public void AddDanoBonus(int bonus) { danoBonus += bonus; }
    public int getDanoBonus() { return danoBonus; }

    // Loop principal de batalha
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

    private void TurnoInimigo() {
        System.out.println("--- Vez do " + inimigo.getNome() + " ---");
        inimigo.ExecutarAcao(heroi, this);
    }
}
