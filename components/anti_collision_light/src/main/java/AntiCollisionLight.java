public class AntiCollisionLight {

    private static AntiCollisionLight instance = new AntiCollisionLight();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

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
            return "AntiCollisionLight // " + manufacturer;
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
