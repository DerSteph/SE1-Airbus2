public class RightAileron {

    private static RightAileron instance = new RightAileron();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private int degree;

    // Private constructor
    private RightAileron() {
        port = new Port();
    }

    // Static method getInstance
    public static RightAileron getInstance() {
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
    public class Port implements IRightAileron {

        @Override
        public String version() {
            return "RightAileron // " + manufacturer + " - " + type + " - " + id;
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
