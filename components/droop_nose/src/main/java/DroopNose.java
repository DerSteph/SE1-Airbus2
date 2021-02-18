public class DroopNose {
    // static instance
    private static DroopNose instance = new DroopNose();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private int degree;

    // private Constructor
    private DroopNose() {
        port = new Port();
    }

    // public static method getInstance
    public static DroopNose getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "DroopNose // " + manufacture + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }

    public int innerFullDown() {
        degree = -100;
        return degree;
    }

    public int innerDown(int degree) {
        this.degree = this.degree-degree;
        return this.degree;
    }

    public int innerUp(int degree) {
        this.degree = this.degree+degree;
        return this.degree;
    }

    // inner class port
    public class Port implements IDroopNose {
        public String version() {
            return innerVersion();
        }
        public int neutral() {
            return innerNeutral();
        }

        public int fullDown() {
            return innerFullDown();
        }

        public int down(int degree) {
            return innerDown(degree);
        }
        public int up(int degree) {
            return innerUp(degree);
        }
    }
}
