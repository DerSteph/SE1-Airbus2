package event.tcas;

public class TCASScan {
    String environment;

    public TCASScan(String environment) {
        this.environment = environment;
    }

    public String toString() {
        return "Event: TCAS - Scan";
    }
}
