import java.util.ArrayList;

/**
 * Classe base que implementa o papel de "Sujeito" no padrão de projeto Observer.
 * Gerencia uma lista de observadores ({@link Subscriber}) e fornece os mecanismos
 * para notificá-los sobre mudanças de estado ou eventos relevantes no jogo.
 */
public class Publisher {

     /**
     * Construtor privado para evitar a instanciação da classe utilitária.
     */
    public Publisher() {
        // Construtor privado para esconder o implícito
    }


    /** Lista de observadores interessados nos eventos disparados por este Publisher. */
    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    /**
     * Adiciona um novo observador à lista de notificações.
     * @param s O objeto que implementa a interface {@link Subscriber} a ser inscrito.
     */
    public void inscrever(Subscriber s) {
        subscribers.add(s);
    }

    /**
     * Remove um observador da lista, interrompendo o recebimento de notificações.
     * @param s O objeto {@link Subscriber} a ser removido.
     */
    public void desinscrever(Subscriber s) {
        subscribers.remove(s);
    }

    /**
     * Dispara um evento para todos os observadores atualmente inscritos.
     * <p>
     * <b>Nota técnica:</b> O método itera sobre uma cópia da lista original 
     * ({@code new ArrayList<>(subscribers)}) para evitar exceções de modificação 
     * concorrente ({@code ConcurrentModificationException}), permitindo que um 
     * efeito se desinscreva (expire) durante o próprio processo de notificação.
     * </p>
     * @param evento Uma String identificando o tipo de evento (ex: "ATAQUE", "FIM_TURNO").
     * @param combate O contexto da batalha utilizado pelos assinantes para processar a lógica.
     */
    protected void notificar(String evento, Combate combate) {
        // Itera sobre uma cópia para evitar erros caso um efeito se desinscreva durante a notificação
        for (Subscriber s : new ArrayList<>(subscribers)) {
            s.serNotificado(evento, combate);
        }
    }
}
