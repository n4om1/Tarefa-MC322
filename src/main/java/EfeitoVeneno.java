//package main.java;

public class EfeitoVeneno extends Efeito {

    public EfeitoVeneno(Entidade dono, int acumulos) {
        super("Veneno", dono, acumulos);
    }

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
