package event.radar;

public class GPSScan {
    String environment;

    public GPSScan(String environment) {
        this.environment = environment;
    }

    public String toString() {
        return "Event: GPS - Scan";
    }
}
