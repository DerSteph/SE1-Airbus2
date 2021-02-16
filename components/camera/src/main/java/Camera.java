public class Camera {
    private static Camera instance = new Camera();

    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private CameraType type;
    private String id = "3326612 / 2516708";

    private boolean isOn;

    private Camera() {
        port = new Port();
    }

    public static Camera getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "TCAS // " + manufacture + " - " + type + " - " + id;
    }

    public CameraType innerSetType(String type) {
        if(type == "taxi")
        {
            this.type = CameraType.taxi;
        }
        if(type == "tail")
        {
            this.type = CameraType.tail;
        }
        if(type == "wing")
        {
            this.type = CameraType.wing;
        }
        return this.type;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public String innerRecord() {
        // ??
        return "record";
    }

    public String innerZoomIn(double factor) {
        return "zoomin";
    }

    public String innerZoomOut(double factor) {
        return "zoomout";
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public class Port implements ICamera {
        public String version()
        {
            return innerVersion();
        }
        public CameraType setType(String type)
        {
            return innerSetType(type);
        }
        public boolean on() {
            return innerOn();
        }
        public String record() {
            return innerRecord();
        }
        public String zoomIn(double factor) {
            return innerZoomIn(factor);
        }
        public String zoomOut(double factor) {
            return innerZoomOut(factor);
        }
        public boolean off() {
            return innerOff();
        }
    }
}
