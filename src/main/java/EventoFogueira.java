import java.util.ArrayList;

/**
 * Evento de fogueira no mapa. O jogador escolhe entre descansar (recuperar vida)
 * ou forjar (melhorar uma carta do baralho).
 *
 * <p>Utiliza o padrão <b>Command</b> ({@link ComandoFogueira}): cada ação disponível
 * é encapsulada como um objeto de comando independente. Para adicionar novas ações
 * à fogueira, basta criar uma nova implementação de {@link ComandoFogueira} e
 * registrá-la aqui — sem alterar o código desta classe.</p>
 */
public class EventoFogueira extends Evento {

    private ArrayList<ComandoFogueira> comandos;

    public EventoFogueira() {
        super("Fogueira");
        comandos = new ArrayList<>();
        comandos.add(new ComandoDescansar());
        comandos.add(new ComandoMelhorarCarta());
    }

    @Override
    public void Iniciar(EstadoJogador estado) {
        System.out.println("=== FOGUEIRA ===");
        System.out.println("Você encontra uma fogueira crepitante. O que deseja fazer?");
        System.out.println();

        for (int i = 0; i < comandos.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + comandos.get(i).getDescricao());
        }
        System.out.print("Escolha: ");

        int escolha = estado.getEntrada().nextInt();
        while (escolha < 1 || escolha > comandos.size()) {
            System.out.print("Opção inválida. Escolha novamente: ");
            escolha = estado.getEntrada().nextInt();
        }

        comandos.get(escolha - 1).Executar(estado);
        System.out.println();
    }
}
