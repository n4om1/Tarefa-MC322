import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        CartaDano cDano1 = new CartaDano("Espada", 1, 3);
        CartaEscudo cEscudo1 = new CartaEscudo("Escudo", 2, 5);

        //while
        Heroi h1 = new Heroi("Herói", 20, cEscudo1.quantidade);
        Inimigo I1 = new Inimigo("Inimigo", cEscudo1.quantidade, 10, 3);
        Scanner entrada = new Scanner(System.in);
        Scanner item = new Scanner(System.in);
    // int protecao = 0;
        while (h1.EstaVivo()) {
             //loja:
            
            int carta;
            boolean uso_loja = true;
            while (uso_loja) {
                
                System.out.println("loja");
                System.out.println("1 - " + cDano1.descrição());
                System.out.println("2 - " + cEscudo1.descrição());
                System.out.println("0 - fechar loja");
                System.out.println();
                carta = item.nextInt();
                switch (carta) {
                    case 1:
                        cDano1.aumenta_numero_cartas();
                        break;
                    case 2:
                        cEscudo1.numero_cartas();
                        break;
                    default:
                        uso_loja = false;
                        break;
                }
            }
            int Energia = 3;
            
            System.out.println(h1.nome + "(" + h1.vida + "/20)");
            System.out.println("vs");
            System.out.println(I1.nome + "(" + I1.vida + "/10)");

            int dano;
            int protecao = 0;
            while (Energia > 0){

                System.out.println("=======");
                System.out.println("Energia disponível: " + Energia + "/3)");
                System.out.println("Escolha sua próxima ação:");
                System.out.println("1 - Usar Carta de Dano");
                System.out.println("2 - Usar Carda de Escudo");
                System.out.println("3 - Encerrar turno");
                System.out.println("=======");
                System.out.println();

                int key = entrada.nextInt();
                
                switch (key) {
                    case 1:
                        if(cDano1.vazio()){
                            System.out.println("Ja se usaram se todos as cartas desse tipo");
                            break;
                        }
                        dano = cDano1.usar(cDano1.nome, cDano1.dano);
                        cDano1.quantidade--;
                        I1.vida = I1.ReceberDano(dano);
                        Energia = Energia - cDano1.custo;
                        break;
                    case 2:
                        if(cEscudo1.vazio()){
                            System.out.println("Ja se usaram se todos as cartas desse tipo");
                            break;
                        }
                        protecao = cEscudo1.usar(cEscudo1.nome);
                        cEscudo1.quantidade--;
                        h1.vida = h1.ReceberEscudo(protecao);
                        Energia = Energia - cEscudo1.custo;
                        break;
                    case 3:
                        break;  //encerrar turno
                }
                if (key == 3)
                    break;
                if (Energia == 0)
                    break;
                if (!I1.EstaVivo())
                    break;
            }

            if(!I1.EstaVivo()){
                System.out.println("O(A) " + I1.nome + " é derrotada.");
                break;
            }else if (!h1.EstaVivo()){
                System.out.println("O(A) " + h1.nome + " é derrotada.");
                break;
            }else if (cDano1.quantidade > 0 || cEscudo1.quantidade > 0 && Energia == 0){
                System.out.println("O jogador encerra o turno (sem energia) e descarta a cartas que sobraram.");
                cDano1.quantidade = 0;
                cEscudo1.quantidade=0;
            }else if (Energia > 0)
                System.out.println("O jogador encerra o turno");
            else
                System.out.println("O jogador encerra o turno (sem energia)");
            
            System.out.println();

            //vez do inimigo

            if (protecao > 0){
                h1.vida = h1.ReceberDano(I1.Atacar());
                System.out.println(I1.nome + "ataca com "+ I1.ataque + " de dano. A defesa absorve parte do dano ("+h1.vida+"/20 de vida restantes)");
            }else{
                h1.vida = h1.ReceberDano(I1.Atacar());
                System.out.println(I1.nome + "ataca com "+ I1.ataque + " de dano. ("+h1.vida+"/20 de vida restantes)");
            }
            System.out.println();
            
        }
        item.close();
        entrada.close();
    }
}

