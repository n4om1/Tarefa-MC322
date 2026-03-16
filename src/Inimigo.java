public class Inimigo {

    String nome;
    int escudo;
    int vida;
    int ataque;

    Inimigo(String nome, int escudo, int vida, int ataque){
        this.nome = nome;
        this.escudo = escudo;
        this.vida = vida;
        this.ataque = ataque;

    }

    int ReceberDano(int dano){
        int vida_final = vida - dano;
        if (vida_final < 0)
            vida_final = 0;
        System.out.println(nome + " recebe " + dano + " de dano ("+ vida_final +"/10 restantes)");
        return vida_final;
    }

    int Atacar(){
        int dano = ataque;
        return dano;
    }

    Boolean EstaVivo(){
        if (vida > 0){
            return true;
        }
        return false;
    }  

}