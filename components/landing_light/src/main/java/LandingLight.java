public class LandingLight {

    private static LandingLight instance = new LandingLight();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;

    // Private constructor
    private LandingLight() {
        port = new Port();
    }

    // Static method getInstance
    public static LandingLight getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        throw new RuntimeException("Not implemented yet.");
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
    public class Port implements ILandingLight {

        @Override
        public String version() {
            return "LandingLight // " + manufacturer + " - " + type + " - " + id;
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
