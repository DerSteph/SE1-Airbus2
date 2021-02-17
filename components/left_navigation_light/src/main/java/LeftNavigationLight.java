public class LeftNavigationLight {

    private static LeftNavigationLight instance = new LeftNavigationLight();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;

    // Private constructor
    private LeftNavigationLight() {
        port = new Port();
    }

    // Static method getInstance
    public static LeftNavigationLight getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        throw new RuntimeException("Not implemented yet.");
    }

    public LightType innerSetLight(String type) {
        return ;
    }

    public Position innerSetPosition(String position) {
        return ;
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
            return "LeftNavigationLight // " + manufacturer + " - " + type + " - " + id;
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
