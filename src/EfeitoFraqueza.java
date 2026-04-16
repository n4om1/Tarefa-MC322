public class EfeitoFraqueza extends Efeito {

    public EfeitoFraqueza(Entidade dono, int acumulos) {
        super("Fraqueza", dono, acumulos);
    }

    @Override
    public void serNotificado(String evento, Combate combate) {
        // Fraqueza reduz o dano causado pelo dono ao atacar
        if (evento.equals("ATAQUE") && combate.getAtacante() == getDono()) {
            combate.AddDanoBonus(-getAcumulos());
            System.out.println("[Fraqueza] " + getDono().getNome() + " está enfraquecido e causa " + getAcumulos() + " de dano a menos!");
        }
    }
}
