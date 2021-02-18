public class LeftAileron {

    private static LeftAileron instance = new LeftAileron();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";
    private int degree;

    // Private constructor
    private LeftAileron() {
        port = new Port();
    }

    // Static method getInstance
    public static LeftAileron getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        degree = 0;
        return degree;
    }

    private int innerFullUp() {
        degree = 60;
        return degree;
    }

    private int innerFullDown() {
        degree = -60;
        return degree;
    }

    private int innerDown(int degree) {
        this.degree -= degree;
        return this.degree;
    }

    private int innerUp(int degree) {
        this.degree += degree;
        return this.degree;
    }

    // Inner class port
    public class Port implements ILeftAileron {

        @Override
        public String version() {
            return "LeftAileron // " + manufacturer;
        }

        @Override
        public int neutral() {
            return innerNeutral();
        }

        @Override
        public int fullUp() {
            return innerFullUp();
        }

        @Override
        public int fullDown() {
            return innerFullDown();
        }

        @Override
        public int down(int degree) {
            return innerDown(degree);
        }

        @Override
        public int up(int degree) {
            return innerUp(degree);
        }
    }
}
