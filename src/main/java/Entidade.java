//package main.java;
import java.util.ArrayList;

public abstract class Entidade {

    private String nome;
    private int vida;
    private int vidaMaxima;
    private int escudo;
    private ArrayList<Efeito> efeitos;

    public Entidade(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.escudo = 0;
        this.efeitos = new ArrayList<>();
    }

    // Aplica dano considerando o escudo atual
    public void ReceberDano(int dano) {
        int danoRestante = dano - escudo;
        escudo = Math.max(0, escudo - dano);
        if (danoRestante > 0) {
            vida = Math.max(0, vida - danoRestante);
        }
    }

    // Adiciona escudo à entidade
    public void GanharEscudo(int quantidade) {
        escudo += quantidade;
    }

    // Retorna se a entidade ainda está viva
    public boolean EstaVivo() {
        return vida > 0;
    }

    // Aplica um efeito com X acúmulos. Se o efeito já existe, soma os acúmulos; senão, cria e inscreve no Publisher
    public void AplicarEfeito(Efeito efeito, Combate combate) {
        for (Efeito e : efeitos) {
            if (e.getNome().equals(efeito.getNome())) {
                e.AdicionarAcumulos(efeito.getAcumulos());
                System.out.println("[Efeito] " + getNome() + " agora tem " + e.getAcumulos() + " acúmulos de " + e.getNome() + ".");
                return;
            }
        }
        efeitos.add(efeito);
        combate.inscrever(efeito);
        System.out.println("[Efeito] " + getNome() + " foi afligido por " + efeito.getString() + ".");
    }

    // Remove um efeito da entidade e o desinscreve do Publisher
    public void RemoverEfeito(Efeito efeito, Combate combate) {
        efeitos.remove(efeito);
        combate.desinscrever(efeito);
    }

    // Retorna uma String com todos os efeitos ativos para exibição no terminal
    public String getEfeitosString() {
        if (efeitos.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(" [");
        for (int i = 0; i < efeitos.size(); i++) {
            sb.append(efeitos.get(i).getString());
            if (i < efeitos.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Zera o escudo (chamado no início de cada turno do jogador)
    public void ZerarEscudo() {
        escudo = 0;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getEscudo() { return escudo; }
    public ArrayList<Efeito> getEfeitos() { return efeitos; }
}
