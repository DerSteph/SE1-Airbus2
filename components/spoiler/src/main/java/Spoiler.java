public class Spoiler {

    private static Spoiler instance = new Spoiler();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private int degree;

    // Private constructor
    private Spoiler() {
        port = new Port();
    }

    // Static method getInstance
    public static Spoiler getInstance() {
        return instance;
    }

    // Methods
    private int innerNeutral() {
        throw new RuntimeException("Not implemented yet.");
    }

    private int innerFullUp() {
        degree = 0;
        return degree;
    }

    private int innerUp(int degree) {
        this.degree += degree;
        return this.degree;
    }

    private int innerDown(int degree) {
        this.degree -= degree;
        return this.degree;
    }

    // Inner class port
    public class Port implements ISpoiler {

        @Override
        public String version() {
            return "Spoiler // " + manufacturer + " - " + type + " - " + id;
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
        public int up(int degree) {
            return innerUp(degree);
        }

        @Override
        public int down(int degree) {
            return innerDown(degree);
        }


    }
}
