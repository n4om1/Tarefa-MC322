/**
 * Implementação de {@link Efeito} que causa dano periódico ao longo do tempo.
 * O Veneno é um debuff que reduz a vida da entidade afetada a cada ciclo de turno,
 * perdendo intensidade (acúmulos) gradualmente até desaparecer.
 * * @author Seu Nome
 * @version 1.0
 */
public class EfeitoVeneno extends Efeito {

    /**
     * Construtor para o efeito de Veneno.
     * @param dono A entidade que sofrerá o dano periódico.
     * @param acumulos A quantidade inicial de dano e a duração do efeito.
     */
    public EfeitoVeneno(Entidade dono, int acumulos) {
        super("Veneno", dono, acumulos);
    }

    /**
     * Reage ao evento de encerramento do turno para aplicar o dano.
     * 
     * Quando o evento {@code "FIM_TURNO_JOGADOR"} é disparado, o efeito:
     * <ol>
     * <li>Causa dano à vida do dono igual ao número atual de acúmulos.</li>
     * <li>Reduz em 1 a quantidade de acúmulos.</li>
     * <li>Se os acúmulos chegarem a zero, solicita ao dono a remoção do efeito 
     * e cancela a inscrição no {@link Combate}.</li>
     * </ol>
     * 
     * @param evento O tipo de evento notificado.
     * @param combate A instância do combate para gerenciar a remoção do efeito.
     */
    @Override
    public void serNotificado(String evento, Combate combate) {
        // Veneno age ao final do turno do jogador
        if (evento.equals("FIM_TURNO_JOGADOR")) {
            System.out.println("[Veneno] " + getDono().getNome() + " sofre " + getAcumulos() + " de dano pelo veneno!");
            getDono().ReceberDano(getAcumulos());
            System.out.println("[Veneno] " + getDono().getNome() + ": " + getDono().getVida() + "/" + getDono().getVidaMaxima() + " de vida.");
            RemoverAcumulos(1);

            if (getAcumulos() <= 0) {
                System.out.println("[Veneno] O efeito de veneno em " + getDono().getNome() + " se dissipou.");
                getDono().RemoverEfeito(this, combate);
            } else {
                System.out.println("[Veneno] Acúmulos restantes: " + getAcumulos() + ".");
            }
        }
    }
}
