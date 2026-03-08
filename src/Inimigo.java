public class Inimigo {

    int nome;
    int escudo;
    int vida;

    int ReceberDano(){
        vida--;
        return vida;
    }

}