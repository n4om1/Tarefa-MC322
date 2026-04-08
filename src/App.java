import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Instancia o herói e o inimigo
        Heroi heroi = new Heroi("Herói", 40, 3);
        Inimigo inimigo = new Inimigo("Rato Gigante", 30, 7);

        // Monta o baralho com cartas variadas
        Baralho baralho = new Baralho();
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        baralho.AdicionarCarta(new CartaDano("Espada", 1, 6));
        baralho.AdicionarCarta(new CartaEscudo("Barreira", 2, 10));
        baralho.AdicionarCarta(new CartaDano("Flechada", 2, 12));
        baralho.Embaralhar();

        System.out.println("Prepare-se para batalhar!");

        int turno = 1;

        while (heroi.EstaVivo() && inimigo.EstaVivo()) {
            System.out.println("=================");
            System.out.println("  TURNO " + turno);
            System.out.println("=================");

            // Início do turno: recupera energia, zera escudo, compra cartas
            heroi.RecuperarEnergia();
            heroi.ZerarEscudo();
            baralho.ComprarCartas(5);

            // Exibe status e intenção do inimigo (desafio extra)
            System.out.println(heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima()
                    + " de vida | " + heroi.getEscudo() + " de escudo");
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima()
                    + " de vida");
            System.out.println("[!] " + inimigo.AnunciarIntencao());
            System.out.println("Compra: " + baralho.getTamanhoCompra()
                    + " | Descarte: " + baralho.getTamanhoDescarte());
            System.out.println();

            // Turno do jogador
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

                cartaEscolhida.Usar(heroi, inimigo);
                baralho.DescartarCarta(escolha - 1);
                System.out.println();

                if (!inimigo.EstaVivo()) break;
            }

            // Cartas não jogadas vão para o descarte
            if (!baralho.MaoVazia()) {
                System.out.println("Cartas restantes na mão vão para o descarte.");
                baralho.DescartarMao();
            }

            if (!inimigo.EstaVivo()) break;

            // Turno do inimigo
            System.out.println();
            System.out.println("--- Vez do " + inimigo.getNome() + " ---");
            inimigo.Atacar(heroi);

            if (heroi.getEscudo() > 0) {
                System.out.println(inimigo.getNome() + " ataca com " + inimigo.getDano()
                        + " de dano. O escudo absorve parte do golpe! "
                        + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
            } else {
                System.out.println(inimigo.getNome() + " ataca com " + inimigo.getDano()
                        + " de dano! " + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
            }

            System.out.println();
            turno++;
        }

        // Resultado final
        System.out.println("=================");
        if (!inimigo.EstaVivo()) {
            System.out.println("Vitória! " + inimigo.getNome() + " foi derrotado em " + (turno - 1) + " turno(s)!");
        } else {
            System.out.println("Derrota! " + heroi.getNome() + " foi derrotado...");
        }
        System.out.println("=================");

        entrada.close();
    }
}