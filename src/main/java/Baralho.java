//package main.java;
import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    private ArrayList<Carta> pilhaCompra;
    private ArrayList<Carta> maoJogador;
    private ArrayList<Carta> pilhaDescarte;

    public Baralho() {
        pilhaCompra = new ArrayList<>();
        maoJogador = new ArrayList<>();
        pilhaDescarte = new ArrayList<>();
    }

    // Adiciona uma carta à pilha de compra
    public void AdicionarCarta(Carta carta) {
        pilhaCompra.add(carta);
    }

    // Embaralha a pilha de compra
    public void Embaralhar() {
        Collections.shuffle(pilhaCompra);
    }

    // Compra X cartas para a mão; se a pilha de compra esgota, recicla o descarte
    public void ComprarCartas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (pilhaCompra.isEmpty()) {
                if (pilhaDescarte.isEmpty()) {
                    System.out.println("Não há mais cartas disponíveis no baralho!");
                    break;
                }
                pilhaCompra.addAll(pilhaDescarte);
                pilhaDescarte.clear();
                Collections.shuffle(pilhaCompra);
                System.out.println("Pilha de compra esgotada! Embaralhando pilha de descarte...");
            }
            maoJogador.add(pilhaCompra.remove(0));
        }
    }

    // Move uma carta da mão para o descarte pelo índice
    public void DescartarCarta(int indice) {
        pilhaDescarte.add(maoJogador.remove(indice));
    }

    // Move toda a mão para o descarte (fim do turno)
    public void DescartarMao() {
        pilhaDescarte.addAll(maoJogador);
        maoJogador.clear();
    }

    public ArrayList<Carta> getMao() { return maoJogador; }
    public int getTamanhoCompra() { return pilhaCompra.size(); }
    public int getTamanhoDescarte() { return pilhaDescarte.size(); }
    public boolean MaoVazia() { return maoJogador.isEmpty(); }
}
