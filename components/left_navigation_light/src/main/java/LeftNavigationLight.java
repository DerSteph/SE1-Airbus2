public class LeftNavigationLight {

    private static LeftNavigationLight instance = new LeftNavigationLight();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

    private boolean isOn;
    private LightType lightType;
    private Position position;

    // Private constructor
    private LeftNavigationLight() {
        port = new Port();
    }

    // Static method getInstance
    public static LeftNavigationLight getInstance() {
        return instance;
    }

    // Methods
    public LightType innerSetLight(String type) {
        this.lightType = LightType.valueOf(type);
        return this.lightType;
    }

    public Position innerSetPosition(String position) {
        this.position = Position.valueOf(position);
        return this.position;
    }

    private boolean innerOn() {
        isOn = true;
        return isOn;
    }

    private boolean innerOff() {
        isOn = false;
        return isOn;
    }


    // Inner class port
    public class Port implements ILeftNavigationLight {

        @Override
        public String version() {
            return "LeftNavigationLight // " + manufacturer;
        }

        @Override
        public LightType setLight(String type) {
            return innerSetLight(type);
        }

        @Override
        public Position setPosition(String position) {
            return innerSetPosition(position);
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
