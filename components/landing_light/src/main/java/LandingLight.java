public class LandingLight {

    private static LandingLight instance = new LandingLight();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";
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
            return "LandingLight // " + manufacturer;
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
