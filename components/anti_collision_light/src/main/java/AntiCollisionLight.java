public class AntiCollisionLight {

    private static AntiCollisionLight instance = new AntiCollisionLight();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;

    // Private constructor
    private AntiCollisionLight() {
        port = new Port();
    }

    // Static method getInstance
    public static AntiCollisionLight getInstance() {
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
    public class Port implements IAntiCollisionLight {

        @Override
        public String version() {
            return "AntiCollisionLight // " + manufacturer + " - " + type + " - " + id;
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
