public interface IAirConditioning {
    String version();

    boolean on();

    boolean off();

    String clean(String airFlow);

    int heat(String airFlow, int temperature);

    int cool(String airFlow, int temperature);
}
