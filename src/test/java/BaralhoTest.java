import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaralhoTest {

    @Test
    public void maoVaziaNoInicio() {
        Baralho b = new Baralho();
        assertTrue(b.MaoVazia());
    }

    @Test
    public void comprarCartasMoveParaMao() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.ComprarCartas(2);
        assertEquals(2, b.getMao().size());
        assertFalse(b.MaoVazia());
    }

    @Test
    public void comprarMaisCartasQueDisponivel() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.ComprarCartas(5);
        // Só havia 1 carta — deve comprar só 1
        assertEquals(1, b.getMao().size());
    }

    @Test
    public void descartarCartaMoveParaDescarte() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.ComprarCartas(1);
        b.DescartarCarta(0);
        assertTrue(b.MaoVazia());
        assertEquals(1, b.getTamanhoDescarte());
    }

    @Test
    public void descartarMaoLimpaTodasAsCartas() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.AdicionarCarta(new CartaEscudo("Escudo", 1, 5));
        b.ComprarCartas(2);
        b.DescartarMao();
        assertTrue(b.MaoVazia());
        assertEquals(2, b.getTamanhoDescarte());
    }

    @Test
    public void reciclaPilhaDescarteQuandoCompraEsgota() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.ComprarCartas(1);
        b.DescartarCarta(0);
        // Compra novamente — deve reciclar o descarte
        b.ComprarCartas(1);
        assertEquals(1, b.getMao().size());
        assertEquals(0, b.getTamanhoDescarte());
    }

    @Test
    public void reiniciarParaNovaBatalhaDevolveTodas() {
        Baralho b = new Baralho();
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.AdicionarCarta(new CartaDano("Espada", 1, 5));
        b.ComprarCartas(1);
        b.DescartarCarta(0);
        // 1 carta na compra, 1 no descarte, 0 na mão
        b.ReiniciarParaNovaBatalha();
        assertEquals(2, b.getTamanhoCompra());
        assertEquals(0, b.getTamanhoDescarte());
        assertTrue(b.MaoVazia());
    }
}