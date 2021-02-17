public class LogoLight {

    private static LogoLight instance = new LogoLight();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;

    // Private constructor
    private LogoLight() {
        port = new Port();
    }

    // Static method getInstance
    public static LogoLight getInstance() {
        return instance;
    }

    // Methods
    private boolean innerOn() {
        isOn = true;
        return isOn;
    }

    private boolean innerOff() {
        isOn = false;
        return isOn;
    }
    
    // Inner class port
    public class Port implements ILogoLight {

        @Override
        public String version() {
            return "LogoLight // " + manufacturer + " - " + type + " - " + id;
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
