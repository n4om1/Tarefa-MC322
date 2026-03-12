public class Heroi {

    String nome;
    int vida;
    int escudo;

    int ReceberDano(){
        vida--;
        return vida;
    }

    int ReceberEscudo(){
        escudo++;
        return escudo;
    }

    Boolean EstaVivo(){
        if (vida > 0){
            return true;
        }
        return false;
    }  

}