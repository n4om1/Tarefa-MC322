//package main.java;

public class EfeitoForca extends Efeito {

    public EfeitoForca(Entidade dono, int acumulos) {
        super("Força", dono, acumulos);
    }

    @Override
    public void serNotificado(String evento, Combate combate) {
        // Força aumenta o dano quando o dono realiza um ataque
        if (evento.equals("ATAQUE") && combate.getAtacante() == getDono()) {
            combate.AddDanoBonus(getAcumulos());
            System.out.println("[Força] " + getDono().getNome() + " ataca com +" + getAcumulos() + " de dano adicional!");
        }
    }
}
