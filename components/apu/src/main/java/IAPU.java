public interface IAPU {
    String version();

    boolean start();

    int increaseRPM(int value);

    int decreaseRPM(int value);

    boolean shutdown();
}
