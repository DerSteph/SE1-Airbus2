public class Rudder {

    private static Rudder instance = new Rudder();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

    private int degree;

    // Private constructor
    private Rudder() {
        port = new Port();
    }

    // Static method getInstance
    public static Rudder getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        degree = 0;
        return degree;
    }

    private int innerFullLeft() {
        degree = -60;
        return degree;
    }

    private int innerFullRight() {
        degree = 60;
        return degree;
    }

    private int innerLeft(int degree) {
        this.degree -= degree;
        return this.degree;
    }

    private int innerRight(int degree) {
        this.degree += degree;
        return this.degree;
    }

    // Inner class port
    public class Port implements IRudder {

        @Override
        public String version() {
            return "Rudder // " + manufacturer;
        }

        @Override
        public int neutral() {
            return innerNeutral();
        }

        @Override
        public int fullLeft() {
            return innerFullLeft();
        }

        @Override
        public int fullRight() {
            return innerFullRight();
        }

        @Override
        public int left(int degree) {
            return innerLeft(degree);
        }

        @Override
        public int right(int degree) {
            return innerRight(degree);
        }
    }
}
