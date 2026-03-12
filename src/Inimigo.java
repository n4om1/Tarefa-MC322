public class Inimigo {

    int nome;
    int escudo;
    int vida;
    int ataque;
    int ReceberDano(){
        vida--;
        return vida;
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