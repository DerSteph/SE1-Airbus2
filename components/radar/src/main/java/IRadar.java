public interface IRadar {
    String version();
    boolean on();
    boolean scan(String environment);
    boolean off();
}
