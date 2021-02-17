public interface ISatCom {
    String version();
    boolean on();
    boolean connect(String satelite, String frequency);
    void send(String request);
    String receive();
    boolean off();
}
