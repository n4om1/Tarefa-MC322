/**
 * Implementação de {@link Efeito} que reduz a capacidade ofensiva da entidade.
 * O efeito de Fraqueza atua subtraindo um valor fixo do dano total causado pelo 
 * dono do efeito sempre que este realiza um ataque.
 */
public class EfeitoFraqueza extends Efeito {

    /**
     * Construtor para o efeito de Fraqueza.
     * @param dono A entidade que sofrerá a redução de dano.
     * @param acumulos O valor a ser subtraído do dano causado a cada ataque.
     */
    public EfeitoFraqueza(Entidade dono, int acumulos) {
        super("Fraqueza", dono, acumulos);
    }

    /**
     * Intercepta o evento de ataque para aplicar a penalidade de dano.
     * <p>
     * Se o evento notificado for {@code "ATAQUE"} e o atacante for o dono deste efeito,
     * o método utiliza um valor negativo dos acúmulos atuais em {@link Combate#AddDanoBonus(int)}
     * para reduzir o dano final processado.
     * </p>
     * @param evento O tipo de evento disparado pelo {@link Publisher}.
     * @param combate A instância do combate onde o cálculo de dano está sendo realizado.
     */
    @Override
    public void serNotificado(String evento, Combate combate) {
        // Fraqueza reduz o dano causado pelo dono ao atacar
        if (evento.equals("ATAQUE") && combate.getAtacante() == getDono()) {
            combate.AddDanoBonus(-getAcumulos());
            System.out.println("[Fraqueza] " + getDono().getNome() + " está enfraquecido e causa " + getAcumulos() + " de dano a menos!");
        }
    }
}
