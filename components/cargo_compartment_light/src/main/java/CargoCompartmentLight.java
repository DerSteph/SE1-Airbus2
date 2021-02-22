public class CargoCompartmentLight {

    private static CargoCompartmentLight instance = new CargoCompartmentLight();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

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
            return "CargoCompartmentLight // " + manufacturer;
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
