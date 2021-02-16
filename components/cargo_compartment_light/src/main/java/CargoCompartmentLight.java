public class CargoCompartmentLight {

    private static CargoCompartmentLight instance = new CargoCompartmentLight();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;

    // Private constructor
    private CargoCompartmentLight() {
        port = new Port();
    }

    // Static method getInstance
    public static CargoCompartmentLight getInstance() {
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
    public class Port implements ICargoCompartmentLight {

        @Override
        public String version() {
            return "CargoCompartmentLight // " + manufacturer + " - " + type + " - " + id;
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public void dim() { }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
