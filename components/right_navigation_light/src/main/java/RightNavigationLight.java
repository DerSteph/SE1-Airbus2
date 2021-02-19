public class RightNavigationLight {
    //static instance
    private static RightNavigationLight instance = new RightNavigationLight();
    //port
    public Port port;

    private String manufacturer = "2814814";
    private LightType type = LightType.green;
    private Position position = Position.starboard;
    private boolean isOn = false;

    private RightNavigationLight() {
        port = new Port();
    }

    public static RightNavigationLight getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "RightNavigationLight1";
    }

    private LightType innerSetLightType(String type) {
        if (type.equals("red")) {
            this.type = LightType.red;
            return LightType.red;
        } else if (type.equals("green")) {
            this.type = LightType.green;
            return LightType.green;
        }
        return null;
    }

    private Position innerSetPosition(String position) {
        if (position.equals("starboard")) {
            this.position = Position.starboard;
            return Position.starboard;
        } else if (position.equals("port")) {
            this.position = Position.port;
            return Position.port;
        }
        return null;
    }

    private boolean innerOn() {
        isOn = true;
        return true;
    }

    private boolean innerOff() {
        isOn = false;
        return false;
    }

    public class Port implements IRightNavigationLight {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public LightType setLightType(String type) {
            return innerSetLightType(type);
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

        public boolean isOn() {
            return isOn;
        }

        public LightType getLightType() {
            return type;
        }

        public Position getPosition() {
            return position;
        }
    }
}
