//package main.java;
public interface Subscriber {
    // Chamado pelo Publisher quando um evento ocorre no combate
    void serNotificado(String evento, Combate combate);
}
