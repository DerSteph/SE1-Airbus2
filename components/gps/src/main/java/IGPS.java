public interface IGPS {
    String version();
    boolean on();
    boolean connect(String satelite);
    String send(String request); // not declared
    String receive();
    boolean off();
}
