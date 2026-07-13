/**
 * Comando concreto da {@link EventoFogueira}: recupera 30% da vida máxima do herói.
 * Implementa o padrão <b>Command</b>.
 */
public class ComandoDescansar implements ComandoFogueira {

    @Override
    public String getDescricao() {
        return "Descansar — Recuperar 30% da vida máxima";
    }

    @Override
    public void Executar(EstadoJogador estado) {
        Heroi heroi = estado.getHeroi();
        int cura = (int) (heroi.getVidaMaxima() * 0.3);
        heroi.Curar(cura);
        System.out.println("Você descansa ao calor da fogueira e recupera " + cura + " de vida.");
        System.out.println(heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " HP.");
    }
}
