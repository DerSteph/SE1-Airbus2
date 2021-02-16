public class Rudder {

    private static RightAileron instance = new Rudder();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

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
            return "Rudder // " + manufacturer + " - " + type + " - " + id;
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
