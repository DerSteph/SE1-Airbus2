public class Slat {

    private static Slat instance = new Slat();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

    private int degree;

    // Private constructor
    private Slat() {
        port = new Port();
    }

    // Static method getInstance
    public static Slat getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        degree = 0;
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
    public class Port implements ISlat {

        @Override
        public String version() {
            return "Slat // " + manufacturer;
        }

        @Override
        public int neutral() {
            return innerNeutral();
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
