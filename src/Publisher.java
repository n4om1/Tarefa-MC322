import java.util.ArrayList;

public class Publisher {

    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    // Inscreve um Subscriber para ser notificado sobre eventos do combate
    public void inscrever(Subscriber s) {
        subscribers.add(s);
    }

    // Desinscreve um Subscriber de modo que ele não seja mais notificado
    public void desinscrever(Subscriber s) {
        subscribers.remove(s);
    }

    // Notifica todos os Subscribers sobre um evento, passando o contexto do combate
    protected void notificar(String evento, Combate combate) {
        // Itera sobre uma cópia para evitar erros caso um efeito se desinscreva durante a notificação
        for (Subscriber s : new ArrayList<>(subscribers)) {
            s.serNotificado(evento, combate);
        }
    }
}
