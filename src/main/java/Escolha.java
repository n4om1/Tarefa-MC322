import java.util.ArrayList;

/**
 * Evento de escolha narrativa no mapa.
 * Apresenta ao jogador uma situação com múltiplas opções, cada uma com
 * consequências distintas (ganho de ouro, perda de vida, cartas, etc.).
 * Herda de {@link Evento}.
 */
public class Escolha extends Evento {

    private String descricao;
    private ArrayList<OpcaoEscolha> opcoes;

    /**
     * @param nome      Nome do evento exibido no mapa.
     * @param descricao Texto narrativo da situação apresentada ao jogador.
     */
    public Escolha(String nome, String descricao) {
        super(nome);
        this.descricao = descricao;
        this.opcoes = new ArrayList<>();
    }

    /** Adiciona uma opção de escolha ao evento. */
    public void AdicionarOpcao(OpcaoEscolha opcao) {
        opcoes.add(opcao);
    }

    /**
     * Apresenta a narrativa e as opções ao jogador e executa o efeito escolhido.
     * @param estado O estado do jogador a ser modificado pela escolha.
     */
    @Override
    public void Iniciar(EstadoJogador estado) {
        System.out.println("=== " + getNome().toUpperCase() + " ===");
        System.out.println(descricao);
        System.out.println();

        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + opcoes.get(i).getDescricao());
        }
        System.out.print("Escolha: ");

        int escolha = estado.getEntrada().nextInt();
        while (escolha < 1 || escolha > opcoes.size()) {
            System.out.print("Opção inválida. Escolha novamente: ");
            escolha = estado.getEntrada().nextInt();
        }

        opcoes.get(escolha - 1).Executar(estado);
        System.out.println();
    }
}
