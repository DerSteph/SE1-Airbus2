public class TailNavigationLight {
    //static instance
    private static TailNavigationLight instance = new TailNavigationLight();
    //Port
    public Port port;
    private String manufacturer = "2814814";
    private boolean isOn = false;

    private TailNavigationLight() {
        port = new Port();
    }

    public static TailNavigationLight getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "TailNavigationLight1";
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    public class Port implements ITailNavigationLight {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }

        public boolean isOn() {
            return isOn;
        }
    }
}
