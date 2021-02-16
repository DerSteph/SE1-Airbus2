public interface ICamera {
    String version();
    CameraType setType(String type);
    boolean on();
    String record();
    String zoomIn(double factor);
    String zoomOut(double factor);
    boolean off();
}
