public class Heroi {

    String nome;
    int vida;
    int escudo;

    Heroi(String nome, int vida, int escudo){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    int ReceberDano(int dano){
        int vida_final = vida - dano;
        if (vida_final > 20)
            vida_final = 20;
        else if (vida < 0)
            vida_final = 0;
        return vida_final;
    }

    int ReceberEscudo(int protecao){
        vida = vida + protecao;
        return vida;
    }

    Boolean EstaVivo(){
        if (vida > 0){
            return true;
        }
        return false;
    }  

}