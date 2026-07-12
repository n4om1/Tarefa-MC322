import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoMapaTest {

    @Test
    public void noSemFilhosEhFinal() {
        NoMapa no = new NoMapa(new Batalha(new Inimigo("Rato", 10, 2)));
        assertTrue(no.isFinal());
    }

    @Test
    public void noComFilhosNaoEhFinal() {
        NoMapa no = new NoMapa(new Batalha(new Inimigo("Rato", 10, 2)));
        NoMapa filho = new NoMapa(new Batalha(new Inimigo("Dragão", 50, 15)));
        no.AdicionarFilho(filho);
        assertFalse(no.isFinal());
    }

    @Test
    public void adicionarFilhoAumentaLista() {
        NoMapa no = new NoMapa(new Batalha(new Inimigo("Rato", 10, 2)));
        no.AdicionarFilho(new NoMapa(new Batalha(new Inimigo("Goblin", 20, 5))));
        no.AdicionarFilho(new NoMapa(new Batalha(new Inimigo("Lobo", 25, 6))));
        assertEquals(2, no.getFilhos().size());
    }

    @Test
    public void visitadoIniciaFalso() {
        NoMapa no = new NoMapa(new Batalha(new Inimigo("Rato", 10, 2)));
        assertFalse(no.isVisitado());
    }

    @Test
    public void setVisitadoAlteraEstado() {
        NoMapa no = new NoMapa(new Batalha(new Inimigo("Rato", 10, 2)));
        no.setVisitado(true);
        assertTrue(no.isVisitado());
    }
}