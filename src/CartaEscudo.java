public class CartaEscudo {
        
    String nome;
    int custo;
    int proteção;
    int quantidade;

    CartaEscudo(String nome, int custo, int proteção){
        this.nome = nome;
        this.custo = custo;
        this.quantidade = 0;
        this.proteção = proteção;
    }

    String descrição(){
        String message = nome + " Custo: " + custo + " Proteção: "+ proteção;
        return message;
    }
    
    void numero_cartas(){
        quantidade++;
    }

    boolean vazio(){
        if (quantidade == 0){
            return true;
        }
        return false;
    }

    int usar(String nome){
        System.out.println("Heroi usa " + nome + " e recebe " + proteção + " de proteção");
        return proteção;
    }
        
}
