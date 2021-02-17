public interface IVHF {
    String version();
    boolean on();
    String[] search();
    String forwardChannel();
    String backwardChannel();
    String selectChannel(String channel);
    boolean off();
}
