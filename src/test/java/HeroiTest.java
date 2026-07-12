import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {

    @Test
    public void danoAbsorvidoTotalmentePoEscudo() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.GanharEscudo(5);
        h.ReceberDano(3);
        assertEquals(10, h.getVida());
        assertEquals(2, h.getEscudo());
    }

    @Test
    public void danoParcialComEscudo() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.GanharEscudo(3);
        h.ReceberDano(5);
        assertEquals(8, h.getVida());
        assertEquals(0, h.getEscudo());
    }

    @Test
    public void danoSemEscudo() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.ReceberDano(4);
        assertEquals(6, h.getVida());
        assertEquals(0, h.getEscudo());
    }

    @Test
    public void vidaNaoFicaNegativa() {
        Heroi h = new Heroi("Teste", 3, 3);
        h.ReceberDano(100);
        assertEquals(0, h.getVida());
    }

    @Test
    public void estaVivoComVida() {
        Heroi h = new Heroi("Teste", 10, 3);
        assertTrue(h.EstaVivo());
    }

    @Test
    public void naoEstaVivoComVidaZero() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.ReceberDano(10);
        assertFalse(h.EstaVivo());
    }

    @Test
    public void gastarEnergiaComSucesso() {
        Heroi h = new Heroi("Teste", 10, 3);
        boolean resultado = h.GastarEnergia(2);
        assertTrue(resultado);
        assertEquals(1, h.getEnergiaAtual());
    }

    @Test
    public void gastarEnergiaSemSaldo() {
        Heroi h = new Heroi("Teste", 10, 3);
        boolean resultado = h.GastarEnergia(5);
        assertFalse(resultado);
        assertEquals(3, h.getEnergiaAtual());
    }

    @Test
    public void recuperarEnergiaRestaurarMaximo() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.GastarEnergia(2);
        h.RecuperarEnergia();
        assertEquals(3, h.getEnergiaAtual());
    }

    @Test
    public void zerarEscudoReseta() {
        Heroi h = new Heroi("Teste", 10, 3);
        h.GanharEscudo(7);
        h.ZerarEscudo();
        assertEquals(0, h.getEscudo());
    }

    @Test
    public void limparEfeitosRemoveTodos() {
        Heroi h = new Heroi("Teste", 10, 3);
        // Sem combate, testamos apenas que a lista fica vazia
        h.LimparEfeitos();
        assertEquals(0, h.getEfeitos().size());
    }
}