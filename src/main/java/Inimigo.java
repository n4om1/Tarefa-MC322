/**
 * Representa um adversário no jogo.
 * A classe gerencia o comportamento automático do inimigo, utilizando um sistema
 * de ações rotativas e o anúncio de intenções para o jogador.
 */
public class Inimigo extends Entidade {

    /** O valor base de dano que o inimigo causa em seus ataques. */
    private int dano;

    /** Lista de strings que define a sequência de comportamentos do inimigo. */
    private String[] acoes;

    /** Índice que aponta para a próxima ação a ser executada no array de ações. */
    private int acaoAtual;

    /**
     * Construtor para criar um novo Inimigo.
     * Inicializa o padrão de ações como "atacar" e "aplicarForca".
     *
     * @param nome O nome do inimigo.
     * @param vida A quantidade inicial de pontos de vida.
     * @param dano O dano base do inimigo.
     */
    public Inimigo(String nome, int vida, int dano) {
        super(nome, vida);
        this.dano = dano;
        this.acoes = new String[]{"atacar", "aplicarForca"};
        this.acaoAtual = 0;
    }

    /**
     * Seleciona e executa a ação atual do inimigo com base no índice rotativo.
     * Após a execução, o índice é incrementado (ou reseta para zero) para a próxima rodada.
     *
     * @param heroi O alvo das ações ofensivas.
     * @param combate A instância do combate atual para registro de eventos e efeitos.
     */
    public void ExecutarAcao(Heroi heroi, Combate combate) {
        switch (acoes[acaoAtual]) {
            case "atacar":
                Atacar(heroi, combate);
                break;
            case "aplicarForca":
                AplicarForcaEmSi(combate);
                break;
        }
        acaoAtual = (acaoAtual + 1) % acoes.length;
    }

    /**
     * Realiza um ataque direto contra o Herói.
     * Calcula o dano final considerando bônus do combate e exibe o resultado 
     * detalhando se houve absorção por escudo ou dano direto à vida.
     *
     * @param heroi O herói que receberá o dano.
     * @param combate O contexto do combate para obter bônus de dano ativos.
     */
    public void Atacar(Heroi heroi, Combate combate) {
        combate.NotificarAtaque(this);
        int danoTotal = Math.max(0, dano + combate.getDanoBonus());
        int escudoAntes = heroi.getEscudo();
        heroi.ReceberDano(danoTotal);

        if (escudoAntes > 0) {
            System.out.println(getNome() + " ataca com " + danoTotal + " de dano. O escudo absorve parte do golpe! "
                    + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
        } else {
            System.out.println(getNome() + " ataca com " + danoTotal + " de dano! "
                    + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " de vida.");
        }
    }

    /**
     * Aplica um efeito de fortalecimento (Força) ao próprio inimigo.
     * Esta ação aumenta o potencial de dano para turnos futuros.
     *
     * @param combate O contexto do combate onde o efeito será registrado.
     */
    private void AplicarForcaEmSi(Combate combate) {
        System.out.println(getNome() + " aplica Força em si mesmo!");
        AplicarEfeito(new EfeitoForca(this, 2), combate);
    }

    /**
     * Retorna uma mensagem descrevendo o que o inimigo fará no próximo turno.
     * Essencial para permitir que o jogador tome decisões estratégicas.
     *
     * @return Uma String contendo a intenção do inimigo.
     */
    public String AnunciarIntencao() {
        switch (acoes[acaoAtual]) {
            case "atacar":
                return getNome() + " pretende causar " + dano + " de dano.";
            case "aplicarForca":
                return getNome() + " pretende aplicar Força em si mesmo.";
            default:
                return getNome() + " está planejando algo...";
        }
    }

    /**
     * Obtém o valor base de dano do inimigo.
     * @return O dano base.
     */
    public int getDano() { return dano; }
}
