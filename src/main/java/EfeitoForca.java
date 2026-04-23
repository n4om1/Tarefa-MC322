/**
 * Implementação de {@link Efeito} que aumenta o potencial ofensivo da entidade.
 * O efeito de Força adiciona um valor bônus fixo a cada ataque realizado pelo dono 
 * do efeito, enquanto ele estiver ativo.
 */
public class EfeitoForca extends Efeito {

    /**
     * Construtor para o efeito de Força.
     * @param dono A entidade que receberá o bônus de dano (pode ser Herói ou Inimigo).
     * @param acumulos O valor de dano adicional que será somado a cada ataque.
     */
    public EfeitoForca(Entidade dono, int acumulos) {
        super("Força", dono, acumulos);
    }

    /**
     * Reage aos eventos de combate para aplicar o bônus de dano.
     * <p>
     * Quando o evento for {@code "ATAQUE"} e a entidade que está atacando for 
     * a detentora deste efeito, o bônus de acúmulos é adicionado ao cálculo 
     * de dano do combate através do método {@link Combate#AddDanoBonus(int)}.
     * </p>
     * @param evento O tipo de evento notificado pelo combate.
     * @param combate A referência da batalha atual para aplicação do bônus.
     */
    @Override
    public void serNotificado(String evento, Combate combate) {
        // Força aumenta o dano quando o dono realiza um ataque
        if (evento.equals("ATAQUE") && combate.getAtacante() == getDono()) {
            combate.AddDanoBonus(getAcumulos());
            System.out.println("[Força] " + getDono().getNome() + " ataca com +" + getAcumulos() + " de dano adicional!");
        }
    }
}
