/**
 * Interface que define o contrato para os observadores do sistema de combate.
 * Qualquer classe que deseje reagir a eventos disparados pelo {@link Publisher} 
 * (como efeitos, conquistas ou registros de log) deve implementar esta interface.
 */
public interface Subscriber {
    
    /**
     * Método invocado automaticamente pelo {@link Publisher} sempre que um 
     * evento relevante ocorre no contexto de um combate.
     * @param evento Uma String que identifica o tipo de evento ocorrido 
     * (ex: "ATAQUE", "FIM_TURNO_JOGADOR").
     * @param combate A instância do {@link Combate} atual, permitindo que o 
     * assinante consulte o estado da batalha ou interaja com 
     * os participantes.
     */
    void serNotificado(String evento, Combate combate);
}