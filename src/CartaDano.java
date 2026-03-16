public class CartaDano {
    
    String nome;
    int custo;
    int dano;
    int quantidade = 0;

    CartaDano(String nome, int custo, int dano){
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;
    }

    String descrição(){
        String message = nome + " Custo: " + custo + " Dano: "+ dano;
        return message;
    }

    void aumenta_numero_cartas(){
        this.quantidade = quantidade + 1;
    }

    boolean vazio(){
        if (quantidade == 0){
            return true;
        }
        return false;
    }

    int usar(String nome, int dano){
        System.out.println("Heroi usa " + nome + " contra Inimigo");
        return dano;
    }
        
    
}
